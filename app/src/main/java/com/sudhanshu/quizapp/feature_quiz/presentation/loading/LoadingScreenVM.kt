package com.sudhanshu.quizapp.feature_quiz.presentation.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.sudhanshu.quizapp.core.utils.Prompts
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.data.data_source.GlobalData
import com.sudhanshu.quizapp.feature_quiz.domain.model.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoadingScreenVM @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val globalData: GlobalData
) : ViewModel() {

    private val config = globalData.configuration.value

    private val questionsCount = config.questionsCount
    private val difficultyLevel = config.level
    private val topics = config.topics
    private var topicsCombined = ""

    private var safety = true

    init {
        topics.forEach { topic ->
            topicsCombined += "$topic, "
        }
        val prompt = Prompts.generateQuizPrompt(
            topics = topicsCombined,
            level = difficultyLevel
        )

        if (safety) {
            safety = false
            viewModelScope.launch {
                try {
                    val response = generativeModel.generateContent(prompt)
                    Utils.log("Response === ${response.text}")
                    val json = extractJson(response.text.toString().trimIndent())
                    Utils.log("Corrected format == $json")
                    val quiz = Gson().fromJson(json, Quiz::class.java)
                    Utils.log("Question 1 = $quiz")
                } catch (e: Exception) {
                    Utils.log(e.toString())
                }
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