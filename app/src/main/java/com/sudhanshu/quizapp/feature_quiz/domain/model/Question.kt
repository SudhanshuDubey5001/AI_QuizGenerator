package com.sudhanshu.quizapp.feature_quiz.domain.model

data class Question(
    val question: String,
    val answer: Int,
    val options: List<String>,
    val explanation: String
)
