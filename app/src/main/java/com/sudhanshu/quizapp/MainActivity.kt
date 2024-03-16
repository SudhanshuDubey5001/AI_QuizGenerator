package com.sudhanshu.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.QuizAppTheme
import com.sudhanshu.quizapp.core.utils.AppConfigurationConstants
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.feature_quiz.presentation.loading.components.LoadingScreen
import com.sudhanshu.quizapp.feature_quiz.presentation.options.components.OptionScreen
import com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components.QuizScreen
import com.sudhanshu.quizapp.feature_quiz.presentation.result.components.ResultScreen
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
                    NavHost(
                        navController = navController,
                        startDestination = AppConfigurationConstants.ENTRY_SCREEN
                    ) {
                        composable(
                            route = Screens.WELCOME
                        ) {
                            WelcomeScreen(onNavigate = { route ->
                                navController.navigate(route)
                            })
                        }
                        composable(
                            route = Screens.TOPICS
                        ) {
                            TopicScreen(onNavigate = { route ->
                                when (route) {
                                    Screens.BACK -> navController.popBackStack()
                                    else -> navController.navigate(route)
                                }
                            })
                        }
                        composable(
                            route = Screens.OPTIONS
                        ) {
                            OptionScreen(onNavigate = { route ->
                                when (route) {
                                    Screens.BACK -> navController.popBackStack()
                                    else -> navController.navigate(route)
                                }
                            })
                        }
                        composable(
                            route = Screens.LOADING
                        ) {
                            LoadingScreen(onNavigate = { route ->
                                navController.navigate(route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                        }
                        composable(
                            route = Screens.QUIZ
                        ) {
                            QuizScreen(onNavigate = { route ->
                                when (route) {
                                    Screens.BACK -> navController.popBackStack()
                                    else -> navController.navigate(route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            })
                        }
                        composable(
                            route = Screens.RESULT
                        ) {
                            ResultScreen(onNavigate = { route ->
                                when (route) {
                                    Screens.BACK -> navController.popBackStack()
                                    else -> navController.navigate(route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}