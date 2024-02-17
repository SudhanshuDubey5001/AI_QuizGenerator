package com.sudhanshu.quizapp.feature_quiz.presentation.options.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudhanshu.quizapp.core.presentation.components.QuizAppNavigationBar
import com.sudhanshu.quizapp.feature_quiz.presentation.options.OptionScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionScreen(
    navController: NavController,
    viewModel: OptionScreenVM = hiltViewModel()
) {

    fun onBackButtonPressed(){
        navController.popBackStack()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Modifier.padding(it)
        Column {
            QuizAppNavigationBar(heading = "Options", onClickBackButton = { onBackButtonPressed() })

        }
    }
}
