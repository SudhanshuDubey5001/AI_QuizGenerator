package com.sudhanshu.quizapp.feature_quiz.presentation.quiz

import androidx.lifecycle.ViewModel
import com.sudhanshu.quizapp.feature_quiz.data.data_source.GlobalData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizScreenVM @Inject constructor(
    private val globalData: GlobalData
) : ViewModel(){

}