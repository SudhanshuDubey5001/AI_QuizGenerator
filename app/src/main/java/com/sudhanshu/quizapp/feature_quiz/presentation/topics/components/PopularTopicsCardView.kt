package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.PopularTopicState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularTopicsCardView(
    topicsList: List<PopularTopicState>,
    onPressed: (topic: PopularTopicState) -> Unit
) {
    val fontFamily = Utils.fontFamily

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        if (topicsList.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 18.dp),
                text = "Popular Topics",
                fontFamily = fontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onTertiary
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                topicsList.forEach { topicState ->
                    SingleTopicView(
                        topicState = topicState,
                        fontFamily = fontFamily,
                        onPressed = onPressed
                    )
                }
            }
        }
    }
}

@Composable
fun SingleTopicView(
    topicState: PopularTopicState,
    fontFamily: FontFamily,
    onPressed: (topic: PopularTopicState) -> Unit
) {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .height(38.dp)
                .clickable {
                    if (!topicState.isSelected) onPressed(topicState)
                },
            colors = CardDefaults.cardColors(
                containerColor = if (topicState.isSelected)
                    MaterialTheme.colorScheme.inverseSurface
                else Color.White
            ),
            shape = ShapeDefaults.Large
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                text = topicState.topic,
                fontFamily = fontFamily,
                color = if (topicState.isSelected) Color.White else Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}