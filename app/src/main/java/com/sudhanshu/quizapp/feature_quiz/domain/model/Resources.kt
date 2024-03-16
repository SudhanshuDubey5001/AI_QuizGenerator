package com.sudhanshu.quizapp.feature_quiz.domain.model

data class Resources(
    val resources: List<Resource> = emptyList()
)

data class Resource(
    val url: String,
    val explanation: String
)
