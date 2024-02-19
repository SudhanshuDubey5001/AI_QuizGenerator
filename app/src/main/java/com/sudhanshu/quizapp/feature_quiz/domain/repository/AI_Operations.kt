package com.sudhanshu.quizapp.feature_quiz.domain.repository

interface AI_Operations {
    suspend fun getResponseFromGenerativeAI(prompt: String): String
}