package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudhanshu.quizapp.R
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.presentation.components.QuizAppNavigationBar
import com.sudhanshu.quizapp.core.presentation.components.SetStatusBarColor
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.TopicScreenVM
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.TopicsScreenEvents
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreen(
    navController: NavController,
    viewModel: TopicScreenVM = hiltViewModel()
) {
    fun onClickBackButton() {
        navController.popBackStack()
    }

    val fontFamily = FontFamily(
        Font(
            R.font.comfortaa, FontWeight.Normal
        )
    )

    val snakbarhostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collectLatest { event ->
            when (event) {
                is UiEvent.showSnackBar -> {
                    snakbarhostState.showSnackbar(
                        message = event.msg
                    )
                }
            }
        }
    }

    SetStatusBarColor(color = MaterialTheme.colorScheme.primary)
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snakbarhostState) }
    ) {
        Modifier.padding(it)
        Column {
            QuizAppNavigationBar(heading = "Topic", onClickBackButton = { onClickBackButton() })
            TopicInput(
                fontFamily = fontFamily,
                inputProp = viewModel.topicProps.value,
                onValueChanged = {
                    viewModel.onEvents(TopicsScreenEvents.onTopicInputEntered(it))
                },
                onSubmitTopic = {
                    viewModel.onEvents(TopicsScreenEvents.onSubmitTopic)
                }
            )
            ExampleTopics(fontFamily = fontFamily)
        }
    }
}