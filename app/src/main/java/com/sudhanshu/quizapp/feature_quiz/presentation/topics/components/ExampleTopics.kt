package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.sudhanshu.quizapp.feature_quiz.presentation.welcome.components.Footer

@Composable
fun ExampleTopics(
    fontFamily: FontFamily
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {

    }
}