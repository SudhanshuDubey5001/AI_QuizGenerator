package com.sudhanshu.quizapp.core.utils

object ErrorMessages {
    const val INVALID_TOPIC = "Umm.. AI is not smart enough to quiz you on that topic"
    const val STILL_LOADING = "Hold on, verifying if AI can create a quiz on that topic"
    const val ALREADY_PRESENT = "You already added that topic"
    const val FAILED_QUIZ_GENERATE_ATTEMPT = "Hey, \nI apologize for the inconvenience. I tried my best but apparently I'm feeling very well, you can either try again or you try at some later point in time.\n\nYours,\nGoogle Gemini"
    const val FAILED_QUIZ_GENERATE_FINAL_ATTEMPT = "Hey, \nI apologize for the inconvenience. I tried my best but apparently I'm still not able to create the quiz. Hope you can understand. Check back later.\n\nYours,\nGoogle Gemini"
}