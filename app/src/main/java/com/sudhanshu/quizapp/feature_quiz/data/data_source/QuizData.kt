package com.sudhanshu.quizapp.feature_quiz.data.data_source

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.Gson
import com.sudhanshu.quizapp.core.utils.MockResponses
import com.sudhanshu.quizapp.feature_quiz.domain.model.Question
import com.sudhanshu.quizapp.feature_quiz.domain.model.Quiz
import com.sudhanshu.quizapp.feature_quiz.domain.model.Resource
import com.sudhanshu.quizapp.feature_quiz.domain.model.Resources
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizData @Inject constructor() {

    private val _quizQuestions = mutableStateListOf<Question>()
    val quizQuestions: SnapshotStateList<Question> = _quizQuestions

    private val _resources = mutableStateListOf<Resource>()
    val resources : SnapshotStateList<Resource> = _resources

    // --------------Mock test--------
    val mockQuiz = Gson().fromJson(MockResponses.response1, Quiz::class.java)
    init {
        _quizQuestions.addAll(mockQuiz.questions)
    }
// ---------------------------------------

    fun setQuizData(data: Quiz) {
        _quizQuestions.addAll(data.questions)
    }

    fun setOptionSelected(
        questionIndex: Int,
        optionSelectedIndex: Int,
        visitedStatus: Boolean
    ) {
        val correctAnswer = quizQuestions[questionIndex].correct_answer
        val userAnswer = quizQuestions[questionIndex].options[optionSelectedIndex]
        _quizQuestions[questionIndex] = _quizQuestions[questionIndex].copy(
            optionSelected = optionSelectedIndex,
            questionAttempted = visitedStatus,
            isCorrect = userAnswer == correctAnswer
        )
    }

    fun setResources(data: Resources){
        _resources.addAll(data.resources)
    }

    fun getTotalCorrectAnswer(): Int{
        var totalCorrect = 0
        _quizQuestions.map{question ->
            if(question.isCorrect) totalCorrect++
        }
        return totalCorrect
    }
}