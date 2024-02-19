package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.presentation.components.QuizAppNavigationBar
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.TopicScreenVM
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.TopicsScreenEvents
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreen(
    onNavigate: (route: String) -> Unit,
    viewModel: TopicScreenVM = hiltViewModel()
) {
    fun onClickBackButton() {
        onNavigate(Screens.BACK)
    }

    fun onClickNextScreenButton(){
        onNavigate(Screens.OPTIONS)
    }

    val snakbarhostState = remember { SnackbarHostState() }
    val topicProps = viewModel.topicProps.value
    val topicsList = viewModel.topicsSelected
    val popularTopics = viewModel.popularTopicsStateHolder

    val scrollState = rememberScrollState()
    val focus = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collectLatest { event ->
            when (event) {
                is UiEvent.showSnackBar -> {
                    snakbarhostState.showSnackbar(
                        message = event.msg
                    )
                }

                is UiEvent.navigate -> Unit
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
                            onClickNextScreenButton()
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
            Column(modifier = Modifier
                .verticalScroll(scrollState)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) {
                    focus.clearFocus()
                }
            ) {
                TopicInput(
                    inputProp = topicProps,
                    onValueChanged = {
                        viewModel.onEvents(TopicsScreenEvents.OnTopicInputEntered(it))
                    },
                    onSubmitTopic = {
                        viewModel.onEvents(TopicsScreenEvents.OnSubmitTopic)
                    }
                )
                TopicsAddedDisplay(
                    topicsList = topicsList,
                    onDeletePressed = { index ->
                        viewModel.onEvents(TopicsScreenEvents.OnTopicDeletePressed(index))
                    })
                Spacer(modifier = Modifier.height(16.dp))
                PopularTopicsCardView(
                    topicsList = popularTopics,
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