package com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.domain.model.Question

@Composable
fun QuizView(
    quiz: Question,
    onOptionSelect: (optionIndex: Int) -> Unit
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
            quiz.options.forEachIndexed { index, option ->
                OptionView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { if(!quiz.questionAttempted) onOptionSelect(index) },
                    index = index,
                    option = option,
                    correctAnswer = quiz.correct_answer,
                    fontSize = fontSize,
                    fontColor = fontColor,
                    optionSelected = quiz.optionSelected,
                    onSelected = { optionIndex ->
                        if(!quiz.questionAttempted) onOptionSelect(optionIndex)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            if (quiz.questionAttempted) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier,
                    text = "Explanation: ",
                    fontFamily = fontFamily,
                    fontSize = fontSize,
                    color = fontColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier,
                    text = quiz.explanation,
                    fontFamily = fontFamily,
                    fontSize = fontSize,
                    color = fontColor
                )
            }
        }
    }
}

@Composable
fun OptionView(
    modifier: Modifier,
    index: Int,
    option: String,
    correctAnswer: String,
    fontColor: Color,
    fontSize: TextUnit,
    optionSelected: Int,
    onSelected: (optionIndex: Int) -> Unit
) {
    Row(
        modifier = if (optionSelected == -1) modifier
        else if (option == correctAnswer) modifier
            .border(2.dp, MaterialTheme.colorScheme.outline, ShapeDefaults.Medium)
        else if (optionSelected == index) modifier
            .border(2.dp, MaterialTheme.colorScheme.error, ShapeDefaults.Medium)
        else modifier
    ) {
        RadioButton(
            modifier = Modifier.padding(end = 10.dp),
            selected = (index == optionSelected),
            onClick = { onSelected(index) },
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = option,
            fontFamily = Utils.fontFamily,
            fontSize = fontSize,
            color = fontColor
        )
    }
}