package com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.domain.model.Question

@Composable
fun QuizView(
    quiz: Question,
) {
    val fontColor = MaterialTheme.colorScheme.onSurface
    val fontFamily = Utils.fontFamily
    val fontSize = 18.sp


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                modifier = Modifier,
                text = quiz.question,
                fontFamily = fontFamily,
                fontSize = fontSize,
                color = fontColor
            )
            Spacer(modifier = Modifier.height(24.dp))
            quiz.options.forEach { option ->
                OptionView(
                    option = option,
                    fontSize = fontSize,
                    fontColor = fontColor
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun OptionView(
    option: String,
    fontColor: Color,
    fontSize: TextUnit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            modifier = Modifier.padding(end = 10.dp),
            selected = false, onClick = { /*TODO*/ })
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = option,
            fontFamily = Utils.fontFamily,
            fontSize = fontSize,
            color = fontColor
        )
    }
}