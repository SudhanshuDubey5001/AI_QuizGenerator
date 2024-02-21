package com.sudhanshu.quizapp.feature_quiz.presentation.result

import androidx.lifecycle.ViewModel
import com.sudhanshu.quizapp.feature_quiz.data.data_source.QuizData
import com.sudhanshu.quizapp.feature_quiz.data.data_source.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultScreenVM @Inject constructor(
    private val quizDataInstance: QuizData,
    private val userDataInstance: UserData
) : ViewModel(){

}