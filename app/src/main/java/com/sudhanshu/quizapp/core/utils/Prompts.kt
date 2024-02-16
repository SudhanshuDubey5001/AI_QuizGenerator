package com.sudhanshu.quizapp.core.utils

object Prompts {
    fun validatePrompt(topic: String): String{
        return "is \"$topic\" a valid topic for which you can generate 10 quiz questions with multiple choices. only answer in yes or no"
    }
}