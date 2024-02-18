package com.sudhanshu.quizapp.feature_quiz.presentation.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudhanshu.quizapp.core.presentation.components.SetStatusBarColor
import com.sudhanshu.quizapp.core.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(
    navController: NavController,
    viewModel: LoadingScreenVM = hiltViewModel()
) {

    SetStatusBarColor(color = MaterialTheme.colorScheme.primary)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Modifier.padding(it)
        Column(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 40.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "AI is creating your quiz",
                    fontSize = 42.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp,
                    fontFamily = Utils.fontFamily
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .size(20.dp),
                    color = Color.White
                )
            }
        }
    }
}