package com.sudhanshu.quizapp.feature_quiz.data.data_source

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.Gson
import com.sudhanshu.quizapp.core.utils.MockResponses
import com.sudhanshu.quizapp.feature_quiz.domain.model.Question
import com.sudhanshu.quizapp.feature_quiz.domain.model.Quiz
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizData @Inject constructor() {


    private val _quizQuestions = mutableStateListOf<Question>()
    val quizQuestions: SnapshotStateList<Question> = _quizQuestions

    // --------------Mock test--------
//    val mockQuiz = Gson().fromJson(MockResponses.response1, Quiz::class.java)
//    init {
//        _quizQuestions.addAll(mockQuiz.questions)
//    }
// ---------------------------------------

    fun setQuizData(data: Quiz) {
        _quizQuestions.addAll(data.questions)
    }

    fun setOptionSelected(
        questionIndex: Int,
        optionSelectedIndex: Int,
        visitedStatus: Boolean
    ) {
        _quizQuestions[questionIndex] = _quizQuestions[questionIndex].copy(
            optionSelected = optionSelectedIndex,
            questionVisitedStatus = visitedStatus
        )
    }
}