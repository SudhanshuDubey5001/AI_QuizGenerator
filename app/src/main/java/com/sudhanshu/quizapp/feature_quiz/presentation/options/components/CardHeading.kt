package com.sudhanshu.quizapp.feature_quiz.presentation.options.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.Utils

@Composable
fun CardHeading(heading: String) {
    Text(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
        text = heading,
        fontFamily = Utils.fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
}