package com.sudhanshu.quizapp.feature_quiz.presentation.loading

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.utils.Prompts
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.data.data_source.GlobalData
import com.sudhanshu.quizapp.feature_quiz.domain.model.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoadingScreenVM @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val globalData: GlobalData
) : ViewModel() {

    private val config = globalData.configuration.value

    private val difficultyLevel = config.level
    private val topics = config.topics
    private var topicsCombined = ""

    private var safety = true
    private var prompt = ""

    private val _retryCountSafety = mutableStateOf(0)   //to limit the retries to 3 API calls
    val retryCountSafety: State<Int> = _retryCountSafety

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    val error = mutableStateOf(false)

    init {
        topics.forEach { topic ->
            topicsCombined += "$topic, "
        }
        prompt = Prompts.generateQuizPrompt(
            topics = topicsCombined,
            level = difficultyLevel
        )

        if (safety) {
            safety = false
            generateQuiz(prompt)
        }
    }

    fun onEvents(event: LoadingScreenEvents) {
        when (event) {
            LoadingScreenEvents.retryGeneratingQuiz -> {
                _retryCountSafety.value++
                generateQuiz(prompt)
                error.value = false
            }
        }
    }

    private fun mockTest(){ // TODO: Remove this method
        viewModelScope.launch {
            delay(2500)
            _retryCountSafety.value++
            error.value = true
        }
    }

    private fun generateQuiz(prompt: String) {
        viewModelScope.launch {
            try {
                Utils.log("----Calling Gemini API----")
                val response = generativeModel.generateContent(prompt)
                Utils.log("Response === ${response.text}")
                val json = extractJson(response.text.toString().trimIndent())
                Utils.log("Corrected format == $json")
                val quiz = Gson().fromJson(json, Quiz::class.java)
                Utils.log("Quiz = $quiz")
                globalData.setQuizData(quiz)
                _uiEvent.emit(UiEvent.navigate(Screens.QUIZ))
            } catch (e: Exception) {
                error.value = true
                Utils.log(e.toString())
            }
        }
    }

    fun extractJson(inputString: String): String? {
        // Find the starting and ending index of the JSON object
        val startIndex = inputString.indexOf("{")
        val endIndex = inputString.lastIndexOf("}")

        return if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            // Extract the JSON substring
            inputString.substring(startIndex, endIndex + 1)
        } else {
            // JSON object not found
            // TODO: Do it again, 3 times then show error page
            inputString
        }
    }
}