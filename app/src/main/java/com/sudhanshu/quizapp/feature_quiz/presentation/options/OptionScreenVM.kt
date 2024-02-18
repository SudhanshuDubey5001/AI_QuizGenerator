package com.sudhanshu.quizapp.feature_quiz.presentation.options

import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.sudhanshu.quizapp.feature_quiz.data.data_source.GlobalData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionScreenVM @Inject constructor(
    private val globalData: GlobalData
) : ViewModel() {

    val config = globalData.configuration

    fun onEvents(event: OptionScreenEvents){
        when(event){
            is OptionScreenEvents.OnValueChangedNameInput -> {
                globalData.setName(event.name)
            }

            is OptionScreenEvents.OnValueChangedQuestionsCount -> {
                globalData.setQuestionCount(event.count.toInt())
            }

            is OptionScreenEvents.OnDifficultyLevelChanged -> {
                globalData.setDifficultyLevel(event.level)
            }
        }
    }
}