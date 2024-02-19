package com.sudhanshu.quizapp.feature_quiz.presentation.quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.utils.AppConfigurationConstants
import com.sudhanshu.quizapp.core.utils.ErrorMessages
import com.sudhanshu.quizapp.core.utils.Prompts
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.data.data_source.QuizConfig
import com.sudhanshu.quizapp.feature_quiz.data.data_source.QuizData
import com.sudhanshu.quizapp.feature_quiz.data.data_source.UserData
import com.sudhanshu.quizapp.feature_quiz.domain.model.Quiz
import com.sudhanshu.quizapp.feature_quiz.domain.repository.AI_Operations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizScreenVM @Inject constructor(
    private val aiOperations: AI_Operations,
    private val quizConfigInstance: QuizConfig,
    private val userDataInstance: UserData,
    private val quizDataInstance: QuizData
) : ViewModel() {

    val quizData = quizDataInstance.quizQuestions
    val userData = userDataInstance.user
    val quizConfig = quizConfigInstance.configuration.value

    private val _loadingMoreQuestions = mutableStateOf(LoadingState.IDLE)
    val loadingMoreQuestions = _loadingMoreQuestions

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var generateQuestionsJob: Job = Job()

    //increment the index as user goes forward in question series
    private var triggerGenerateMoreQuestionIndex = mutableStateOf(1)
    //keeping track of api call retries
    private var retryCount = mutableStateOf(0)

    private fun generateMoreQuestions() {
        _loadingMoreQuestions.value = LoadingState.LOADING
        val topics = quizConfig.topics.joinToString(separator = ", ")
        val level = quizConfig.level
        val prompt = Prompts.generateQuizPromptForNextQuestions(
            topics = topics,
            level = level
        )
        generateQuestionsJob = viewModelScope.launch(Dispatchers.Default) {
            try {
                val response = aiOperations.getResponseFromGenerativeAI(prompt)
                Utils.log(response)
                val quizData = Gson().fromJson(response, Quiz::class.java)
                Utils.log("More questions = $quizData")
                quizDataInstance.setQuizData(quizData)
                incrementTriggerIndex()
                resetRetryCount()
            } catch (e: Exception) {
                if(retryCount.value<=AppConfigurationConstants.GENERATIVE_AI_API_CALL_RETRY_LIMIT)
                    _loadingMoreQuestions.value = LoadingState.ERROR
                else _loadingMoreQuestions.value = LoadingState.STOP_RETRY

                _uiEvent.emit(UiEvent.showSnackBar(ErrorMessages.FAILED_MORE_QUIZ_QUESTIONS_GENERATE))
                Utils.log(e.toString())
            }
            _loadingMoreQuestions.value = LoadingState.IDLE
        }
    }

    fun onEvents(event: QuizScreenEvents) {
        when (event) {
            is QuizScreenEvents.OnSelectOption -> {

            }

            is QuizScreenEvents.SendPageSelectedEvent -> {
                if (event.page == triggerGenerateMoreQuestionIndex.value) {
                    generateMoreQuestions()
                }
            }

            QuizScreenEvents.RetryLoadingMoreQuestions -> {
                generateMoreQuestions()
                incrementRetryCount()
            }
        }
    }

    private fun cancelGenerateMoreQuestionsAPICall(){
        generateQuestionsJob.cancel()
    }

    private fun incrementTriggerIndex() {
        triggerGenerateMoreQuestionIndex.value + AppConfigurationConstants.QUESTIONS_COUNT_PER_API_CALL
    }

    private fun resetRetryCount(){
        retryCount.value = 0
    }

    private fun incrementRetryCount(){
        retryCount.value++
    }
}