package com.sudhanshu.quizapp.feature_quiz.domain.model

data class User(
    val name: String = "",
    val answers: List<Int> = emptyList(),
    val totalScore: Int = 0
)
