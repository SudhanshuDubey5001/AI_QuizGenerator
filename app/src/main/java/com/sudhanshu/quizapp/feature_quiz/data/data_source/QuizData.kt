package com.sudhanshu.quizapp.feature_quiz.data.data_source

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sudhanshu.quizapp.feature_quiz.domain.model.Question
import com.sudhanshu.quizapp.feature_quiz.domain.model.Quiz
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizData @Inject constructor() {

    // Mock test
    //    val mockQuiz = Gson().fromJson(MockResponses.response1, Quiz::class.java)
    //    private val _quizData = mutableStateOf(mockQuiz)

    private val _quizQuestions = mutableStateListOf<Question>()
    val quizQuestions: SnapshotStateList<Question> = _quizQuestions

    fun setQuizData(data: Quiz){
        _quizQuestions.addAll(data.questions)
    }
}