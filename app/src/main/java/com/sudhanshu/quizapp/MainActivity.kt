package com.sudhanshu.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.QuizAppTheme
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.feature_quiz.presentation.loading.components.LoadingScreen
import com.sudhanshu.quizapp.feature_quiz.presentation.options.components.OptionScreen
import com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components.QuizScreen
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.components.TopicScreen
import com.sudhanshu.quizapp.feature_quiz.presentation.welcome.components.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screens.TOPICS) {
                        composable(
                            route = Screens.WELCOME
                        ){
                            WelcomeScreen(navController = navController)
                        }
                        composable(
                            route = Screens.TOPICS
                        ){
                            TopicScreen(navController = navController)
                        }
                        composable(
                            route = Screens.OPTIONS
                        ){
                            OptionScreen(navController = navController)
                        }
                        composable(
                            route = Screens.LOADING
                        ){
                            LoadingScreen(navController = navController)
                        }
                        composable(
                            route = Screens.QUIZ
                        ){
                            QuizScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}