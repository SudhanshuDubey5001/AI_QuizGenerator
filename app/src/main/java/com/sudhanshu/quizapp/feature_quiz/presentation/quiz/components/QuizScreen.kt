package com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudhanshu.quizapp.feature_quiz.presentation.quiz.QuizScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizScreenVM = hiltViewModel()
) {
    Scaffold(
        modifier =Modifier.fillMaxSize()
    ) {
        Modifier.padding(it)
        Text(text = "Quiz screen!!")
    }
}