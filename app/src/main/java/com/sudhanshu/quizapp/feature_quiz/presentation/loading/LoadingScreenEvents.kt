package com.sudhanshu.quizapp.feature_quiz.presentation.loading

sealed class LoadingScreenEvents {
    object retryGeneratingQuiz : LoadingScreenEvents()
}