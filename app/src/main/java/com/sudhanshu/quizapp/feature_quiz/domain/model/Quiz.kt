package com.sudhanshu.quizapp.feature_quiz.domain.model

data class Quiz(
    val questions: List<Question>
)

data class Question(
    val question: String,
    val difficulty: String,
    val correct_answer: String,
    val options: List<String>,
    val explanation: String
)


