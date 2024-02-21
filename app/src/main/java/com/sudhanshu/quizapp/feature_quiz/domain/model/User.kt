package com.sudhanshu.quizapp.feature_quiz.domain.model

data class User(
    val name: String = "",
    val answers: Map<Int, String> = emptyMap(),
    val totalScore: Int = 0
)
