package com.sudhanshu.quizapp.feature_quiz.presentation.topics

data class TopicState (
    val name: String = "",
    val isValid: Boolean = false,
    val loading: Boolean = false
)