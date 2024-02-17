package com.sudhanshu.quizapp.feature_quiz.presentation.options

import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.sudhanshu.quizapp.feature_quiz.data.data_source.GlobalData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OptionScreenVM @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val globalData: GlobalData
) : ViewModel(){

}