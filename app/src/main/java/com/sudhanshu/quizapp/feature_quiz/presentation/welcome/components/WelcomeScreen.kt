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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sudhanshu.quizapp.R
import com.sudhanshu.quizapp.core.presentation.components.SetStatusBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    navController: NavController
) {
    val fontFamily = FontFamily(
        Font(
            R.font.comfortaa, FontWeight.Normal
        )
    )

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
                Header(fontFamily = fontFamily)
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
                Footer(fontFamily = fontFamily, navController = navController)
            }
        }
    }
}

//@Preview
//@Composable
//fun WelcomeScreenPreview() {
//    WelcomeScreen()
//}