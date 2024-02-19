package com.sudhanshu.quizapp.feature_quiz.presentation.quiz

sealed class QuizScreenEvents{
    data class OnSelectOption(val option: Int): QuizScreenEvents()

    data class SendPageSelectedEvent(val page: Int) : QuizScreenEvents()

    object RetryLoadingMoreQuestions : QuizScreenEvents()
}
