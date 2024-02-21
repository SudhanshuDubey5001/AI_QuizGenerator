package com.sudhanshu.quizapp.feature_quiz.presentation.quiz

import com.sudhanshu.quizapp.feature_quiz.domain.model.QuestionVisitedStates

sealed class QuizScreenEvents{
    data class SendPageSelectedEvent(val page: Int) : QuizScreenEvents()

    object RetryLoadingMoreQuestions : QuizScreenEvents()

    data class OnOptionSelected(
        val questionIndex: Int,
        val optionIndex: Int,
        val questionVisitedStateChange: Boolean): QuizScreenEvents()
}
