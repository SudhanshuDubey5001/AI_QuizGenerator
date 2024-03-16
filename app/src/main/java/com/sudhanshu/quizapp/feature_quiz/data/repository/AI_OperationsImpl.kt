package com.sudhanshu.quizapp.feature_quiz.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.domain.repository.AI_Operations

class AI_OperationsImpl(
    private val generativeModel: GenerativeModel
) : AI_Operations{
    override suspend fun gAI_generateAIResponse(prompt: String) : String{
        Utils.log("---Calling Gemini API-----")
        val response = generativeModel.generateContent(prompt)
        //need to extract the JSON as the response can contain other strings
        return Utils.extractJson(response.text.toString().trimIndent())
    }
}