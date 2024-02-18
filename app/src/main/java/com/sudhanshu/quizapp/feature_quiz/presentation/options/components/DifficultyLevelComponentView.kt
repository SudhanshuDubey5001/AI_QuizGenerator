package com.sudhanshu.quizapp.feature_quiz.presentation.options.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.Utils

@Composable
fun DifficultyLevelComponentView(
    selectionChange: (value: Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            val list = listOf("Easy", "Medium", "Hard")
            SegmentedButtons(
                fontSize = 16.sp,
                items = list, onItemSelection = { value ->
                    selectionChange(value)
                }
            )
        }
    }
}