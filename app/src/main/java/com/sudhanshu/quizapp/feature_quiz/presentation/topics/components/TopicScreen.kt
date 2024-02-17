package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudhanshu.quizapp.R
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.presentation.components.QuizAppNavigationBar
import com.sudhanshu.quizapp.core.presentation.components.SetStatusBarColor
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.feature_quiz.data.data_source.GlobalData
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.TopicScreenVM
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.TopicsScreenEvents
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    val topicProps = viewModel.topicProps.value
    val topicsList = viewModel.topicsSelected
    val popularTopics = viewModel.popularTopicsStateHolder

    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

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

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snakbarhostState) },
        floatingActionButton = {
            if (topicsList.isNotEmpty()) {
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 40.dp),
                    onClick = {
                        if (topicsList.isNotEmpty())
                            navController.navigate(Screens.OPTIONS)
                    },
                    elevation = FloatingActionButtonDefaults.elevation(10.dp),
                    containerColor = MaterialTheme.colorScheme.tertiary
                ) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "save note")
                }
            }
        }
    ) {
        Modifier.padding(it)
        Column {
            QuizAppNavigationBar(heading = "Topic", onClickBackButton = { onClickBackButton() })
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                TopicInput(
                    fontFamily = fontFamily,
                    inputProp = topicProps,
                    onValueChanged = {
                        viewModel.onEvents(TopicsScreenEvents.OnTopicInputEntered(it))
                    },
                    onSubmitTopic = {
                        viewModel.onEvents(TopicsScreenEvents.OnSubmitTopic)
                    }
                )
                TopicsAddedDisplay(
                    fontFamily = fontFamily,
                    topicsList = topicsList,
                    onDeletePressed = { index ->
                        viewModel.onEvents(TopicsScreenEvents.OnTopicDeletePressed(index))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                PopularTopicsCardView(
                    topicsList = popularTopics,
                    fontFamily = fontFamily,
                    onPressed = { topicState ->
                        viewModel.onEvents(
                            TopicsScreenEvents.OnTopicSelectedFromPopularTopics(
                                topicState
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}