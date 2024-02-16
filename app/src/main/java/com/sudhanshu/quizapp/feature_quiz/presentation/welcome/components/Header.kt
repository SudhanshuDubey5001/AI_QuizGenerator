package com.sudhanshu.quizapp.feature_quiz.presentation.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Header(fontFamily: FontFamily) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "AI Quiz",
        fontFamily = fontFamily,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = "Unlimited Topics and Questions",
        fontFamily = fontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
}