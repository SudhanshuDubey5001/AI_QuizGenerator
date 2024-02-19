package com.sudhanshu.quizapp.feature_quiz.presentation.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sudhanshu.quizapp.core.presentation.components.SetStatusBarColor
import com.sudhanshu.quizapp.core.utils.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    onNavigate: (route: String) -> Unit
) {
    fun navigateToTopicScreen(){
        onNavigate(Screens.TOPICS)
    }

    SetStatusBarColor(color = MaterialTheme.colorScheme.primary)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Modifier.padding(it)
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Header()
            }
            Card(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(5.dp),
            ) {
                Footer(navigateToTopicScreen = { navigateToTopicScreen() })
            }
        }
    }
}

//@Preview
//@Composable
//fun WelcomeScreenPreview() {
//    WelcomeScreen()
//}