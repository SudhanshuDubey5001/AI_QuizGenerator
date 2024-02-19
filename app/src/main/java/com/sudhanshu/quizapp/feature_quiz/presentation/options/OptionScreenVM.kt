package com.sudhanshu.quizapp.feature_quiz.presentation.options

import androidx.lifecycle.ViewModel
import com.sudhanshu.quizapp.feature_quiz.data.data_source.QuizConfig
import com.sudhanshu.quizapp.feature_quiz.data.data_source.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionScreenVM @Inject constructor(
    private val quizConfig: QuizConfig,
    private val userData: UserData
) : ViewModel() {

    val config = quizConfig.configuration
    val user = userData.user

    fun onEvents(event: OptionScreenEvents){
        when(event){
            is OptionScreenEvents.OnValueChangedNameInput -> {
                userData.setName(event.name)
            }

            is OptionScreenEvents.OnValueChangedQuestionsCount -> {
                quizConfig.setQuestionCount(event.count.toInt())
            }

            is OptionScreenEvents.OnDifficultyLevelChanged -> {
                quizConfig.setDifficultyLevel(event.level)
            }
        }
    }
}