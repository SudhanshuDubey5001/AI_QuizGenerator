package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.ErrorMessages

@Composable
fun ErrorMessageDisplay() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        shape = ShapeDefaults.ExtraSmall
    ) {
        Text(
            modifier = Modifier.padding(14.dp),
            text = ErrorMessages.INVALID_TOPIC,
            fontSize = 12.sp
        )
    }
}