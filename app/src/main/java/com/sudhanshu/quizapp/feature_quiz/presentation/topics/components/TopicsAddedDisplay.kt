package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopicsAddedDisplay(
    fontFamily: FontFamily,
    topicsList: List<String>,
    onDeletePressed: (index: Int) -> Unit
) {
//    val sampleList = listOf("Big bang theory", "Stuart", "Star Wars The return of Jedi")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        if (topicsList.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 18.dp),
                text = "Topics Selected",
                fontFamily = fontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onTertiary
            )
            FlowRow(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                topicsList.forEach { topic ->
                    TopicListComponent(
                        topic = topic,
                        index = topicsList.indexOf(topic),
                        fontFamily = fontFamily,
                        onDeletePressed = onDeletePressed
                    )
                }
            }
        }
    }
}

@Composable
fun TopicListComponent(
    topic: String,
    index: Int,
    fontFamily: FontFamily,
    onDeletePressed: (index: Int) -> Unit
) {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier.height(38.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = ShapeDefaults.Large
        ) {
            Row(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 10.dp)
                        .clickable {
                            onDeletePressed(index)
                        },
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete",
                    tint = Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    text = topic,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}