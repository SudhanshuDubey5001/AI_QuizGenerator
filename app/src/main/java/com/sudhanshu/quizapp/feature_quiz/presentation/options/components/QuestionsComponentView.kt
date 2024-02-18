package com.sudhanshu.quizapp.feature_quiz.presentation.options.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.Utils

@Composable
fun QuestionsComponentView(
    count: Int,
    onPressQuestionCounter: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp),
            text = "Total \nquestions",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontFamily = Utils.fontFamily
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            CardCustom(
                modifier = Modifier.clickable {
                    onPressQuestionCounter()
                },
                color = MaterialTheme.colorScheme.scrim
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = count.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                )
            }
        }
    }
}