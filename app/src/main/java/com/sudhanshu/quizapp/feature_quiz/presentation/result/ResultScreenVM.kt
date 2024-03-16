package com.sudhanshu.quizapp.feature_quiz.presentation.result

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.utils.AppConfigurationConstants
import com.sudhanshu.quizapp.core.utils.MockResponses
import com.sudhanshu.quizapp.core.utils.Prompts
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.data.data_source.QuizConfig
import com.sudhanshu.quizapp.feature_quiz.data.data_source.QuizData
import com.sudhanshu.quizapp.feature_quiz.data.data_source.UserData
import com.sudhanshu.quizapp.feature_quiz.domain.model.Resources
import com.sudhanshu.quizapp.feature_quiz.domain.repository.AI_Operations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ResultScreenVM @Inject constructor(
    private val aiOperations: AI_Operations,
    private val quizDataInstance: QuizData,
    private val userDataInstance: UserData,
    private val quizConfig: QuizConfig
) : ViewModel(){

    private val config = quizConfig.configuration.value
    private val topics = config.topics
    private var topicsCombined = ""
    private var prompt = ""
    private var safety = true

    val questions = quizDataInstance.quizQuestions
    val userData = userDataInstance.user
    val resources = quizDataInstance.resources
    val resultState = mutableStateOf(ResultState())
    init {
        resultState.value = calculateScore()
        topics.forEach { topic ->
            topicsCombined += "$topic, "
        }
//        prompt = Prompts.generateResources(topics = topicsCombined)
        prompt = Prompts.generateResources(topics = "Science, Animals, Medical science")
        if(safety){
            safety = false
            generateResources(prompt)
        }
    }

    fun onEvents(event: ResultScreenEvents){
        when(event){
            else -> {}
        }
    }

    private fun calculateScore(): ResultState{
        val totalCorrect = 17f
        val totalNumberOfQuestions = 20f

//        val totalCorrect : Float = quizDataInstance.getTotalCorrectAnswer().toFloat()
        Utils.log("Total correct = $totalCorrect")
//        val totalNumberOfQuestions : Float = quizDataInstance.quizQuestions.size.toFloat()
        Utils.log("Total questions = $totalNumberOfQuestions")
        val percentage : Float = (totalCorrect/totalNumberOfQuestions)*100
        Utils.log("Percentage = $percentage")

        var grade: Grade = Grade.VERY_BAD
        var color: Color = AppConfigurationConstants.COLOR_VERY_BAD

        when {
            percentage>=0 && percentage<16.67 -> {
                grade = Grade.VERY_BAD
                color = AppConfigurationConstants.COLOR_VERY_BAD
            }
            percentage>=16.67 && percentage<33.34 -> {
                grade = Grade.BAD
                color = AppConfigurationConstants.COLOR_BAD
            }
            percentage>=33.34 && percentage<50 -> {
                grade = Grade.FINE
                color = AppConfigurationConstants.COLOR_FINE
            }
            percentage>=50 && percentage<66.67 -> {
                grade = Grade.GOOD
                color = AppConfigurationConstants.COLOR_GOOD
            }
            percentage>=66.67 && percentage<83.34 -> {
                grade = Grade.VERY_GOOD
                color = AppConfigurationConstants.COLOR_VERY_GOOD
            }
            percentage>=83.34 -> {
                grade = Grade.EXCELLENT
                color = AppConfigurationConstants.COLOR_EXCELLENT
            }
        }
        return ResultState(
            scoreText = "${totalCorrect.toInt()}/${totalNumberOfQuestions.toInt()}",
            scorePercentage = percentage/100,
            grade = grade,
            progressIndicatorColor = color
        )
    }

    private fun generateResources(prompt: String) {
        viewModelScope.launch {
            try {
//                val response = aiOperations.gAI_generateAIResponse(prompt)
                val response = MockResponses.resources  //mock resource
                Utils.log("Corrected format == $response")
                val resources = Gson().fromJson(response, Resources::class.java)
                Utils.log("Resources = $resources")
                quizDataInstance.setResources(resources)
            } catch (e: Exception) {
                Utils.log(e.toString())
            }
        }
    }
}