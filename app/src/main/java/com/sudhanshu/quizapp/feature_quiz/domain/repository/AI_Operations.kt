package com.sudhanshu.quizapp.feature_quiz.domain.repository

interface AI_Operations {
    suspend fun gAI_validatePromptForQuizTopic(prompt: String): String
}