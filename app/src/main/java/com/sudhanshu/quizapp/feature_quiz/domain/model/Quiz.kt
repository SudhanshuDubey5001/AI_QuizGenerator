package com.sudhanshu.quizapp.feature_quiz.domain.model

data class Quiz(
    val questions: List<Question> = emptyList()
)

data class Question(
    val question: String = "",
    val difficulty: String = "",
    val correct_answer: String = "",
    val options: List<String> = emptyList(),
    val explanation: String = "",
    val optionSelected: Int = -1,
    val questionAttempted: Boolean = false,
    val isCorrect: Boolean = false
)


