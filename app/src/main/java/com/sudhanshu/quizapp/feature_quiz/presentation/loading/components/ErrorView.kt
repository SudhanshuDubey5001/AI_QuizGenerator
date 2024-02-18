package com.sudhanshu.quizapp.feature_quiz.presentation.loading.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.AppConfigurationConstants
import com.sudhanshu.quizapp.core.utils.ErrorMessages
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.presentation.loading.LoadingScreenEvents

@Composable
fun ErrorView(
    retrySafetyCount: Int,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer
            ),
            elevation = CardDefaults.elevatedCardElevation(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(14.dp),
                text = if (retrySafetyCount <= AppConfigurationConstants.GENERATIVE_AI_API_CALL_RETRY_LIMIT)
                    ErrorMessages.FAILED_QUIZ_GENERATE_ATTEMPT
                else ErrorMessages.FAILED_QUIZ_GENERATE_FINAL_ATTEMPT,
                fontSize = 16.sp,
                fontFamily = Utils.fontFamily,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (retrySafetyCount <= AppConfigurationConstants.GENERATIVE_AI_API_CALL_RETRY_LIMIT)
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                onClick = { onRetryClick() },
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Retry",
                    fontFamily = Utils.fontFamily,
                    fontSize = 24.sp
                )
            }
    }
}