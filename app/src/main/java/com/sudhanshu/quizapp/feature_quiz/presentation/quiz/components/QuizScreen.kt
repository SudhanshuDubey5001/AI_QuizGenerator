package com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sudhanshu.quizapp.R
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.presentation.components.GifView
import com.sudhanshu.quizapp.core.presentation.components.QuizAppNavigationBar
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.presentation.quiz.LoadingState
import com.sudhanshu.quizapp.feature_quiz.presentation.quiz.QuizScreenEvents
import com.sudhanshu.quizapp.feature_quiz.presentation.quiz.QuizScreenVM
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    onNavigate: (route: String) -> Unit,
    viewModel: QuizScreenVM = hiltViewModel()
) {
    val questionsList = viewModel.quizData
    val isLoadingMoreQuestions = viewModel.loadingMoreQuestions.value

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {
        questionsList.size
    })
    val snackbarHostState = remember { SnackbarHostState() }
    val lastpage = remember { mutableStateOf(questionsList.lastIndex) }

    fun onClickBackButton() {
        onNavigate(Screens.BACK)
    }

    fun onClickSubmitButton() {
        onNavigate(Screens.RESULT)
    }

    fun scrollToNextPage() {
        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
    }

    LaunchedEffect(Unit) {
        launch {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                viewModel.onEvents(QuizScreenEvents.SendPageSelectedEvent(page))
            }
        }
        launch {
            viewModel.uiEvent.collectLatest { event ->
                Utils.log("This is running!!")
                when (event) {
                    is UiEvent.showSnackBar -> {
                        snackbarHostState.showSnackbar(
                            message = event.msg,
                            duration = SnackbarDuration.Short
                        )
                    }

                    is UiEvent.navigate -> {}
                }
            }
        }
    }

    LaunchedEffect(questionsList.size) {
        //to scroll to next page if user is on last page and waiting for the questions to load
        if (pagerState.currentPage == lastpage.value) {
            lastpage.value = questionsList.lastIndex
            scrollToNextPage()
        }
        // TODO: fix this its not working properly
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Modifier.padding(it)
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            QuizAppNavigationBar(
                onClickBackButton = { onClickBackButton() },
                trailingText = "Finish",
                onClickTrailingText = {
                    onClickSubmitButton()
                }
            )

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Question ${pagerState.currentPage + 1}",
                        fontFamily = Utils.fontFamily,
                        fontSize = 32.sp,
                    )
                    if (isLoadingMoreQuestions == LoadingState.LOADING) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            color = Color.White
                        )
                    } else if (isLoadingMoreQuestions == LoadingState.ERROR) {
                        Icon(
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    viewModel.onEvents(QuizScreenEvents.RetryLoadingMoreQuestions)
                                },
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "retry loading questions"
                        )
                    }

                }

                ScrollableQuestionsView(
                    quizData = questionsList,
                    questionsPagerState = pagerState,
                )

                Spacer(modifier = Modifier.height(32.dp))

                HorizontalPager(
                    modifier = Modifier,
                    state = pagerState,
                    beyondBoundsPageCount = 5
                ) { page ->
                    QuizView(quiz = questionsList[page], onOptionSelect = { optionIndex ->
                        viewModel.onEvents(
                            QuizScreenEvents.OnOptionSelected(
                                questionIndex = page,
                                optionIndex = optionIndex,
                                questionVisitedStateChange = true
                            )
                        )
//                        scrollToNextPage()
                    })
                }

                Spacer(modifier = Modifier.height(32.dp))

                Footer(
                    pagerState = pagerState,
                    onClickSubmitButton = {
                        onClickSubmitButton()
                    })

                Spacer(modifier = Modifier.height(30.dp))
            }
        }

    }
}