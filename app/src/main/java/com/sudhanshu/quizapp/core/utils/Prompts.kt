package com.sudhanshu.quizapp.core.utils

import com.sudhanshu.quizapp.feature_quiz.domain.model.Level

object Prompts {
    fun validatePrompt(topic: String): String{
        return "is \"$topic\" a valid topic for which you can generate 10 quiz questions with multiple choices. only answer in yes or no"
    }

    fun generateQuizPrompt(
        topics: String,
        level: Level
    ): String{
        return "Generate a quiz on the topics, \"$topics\" with the following properties - \n" +
                "1. Every question must have 4 options\n" +
                "2. There must be only 1 correct answer\n" +
                "3. There must be in total \"5\" questions\n" +
                "4. There must be also an explanation of the correct answer\n" +
                "5. There are three levels of difficulty - Easy, Medium, Hard. Generate the questions with the difficulty level - \"$level\".   \n" +
                "6. The response needs to be in JSON \n" +
                "7. Do not write anything else except the JSON I requested.\n" +
                "8. Make sure the format of json follows the below data class so I can parse your response easily - \n"+
                "data class Quiz(\n" +
                "    val questions: List<Question>\n" +
                ")\n" +
                "\n" +
                "data class Question(\n" +
                "    val question: String,\n" +
                "    val difficulty: String,\n" +
                "    val correct_answer: String,\n" +
                "    val options: List<String>,\n" +
                "    val explanation: String\n" +
                ")"+
                "Give me a proper JSON format response. Make sure the JSON is properly written with accurate opening and closing braces and brackets and the strings are inside double quotes. Make sure you use some kind of JSON verification function to check the generated JSON and if there is any error then fix it and verify it again. \n" +
                "I want the full response so don't cut the response in mid way. PLEASE DOUBLE CHECK THE JSON FORMATTING as it is crucial that it is 100% correct. Also, please make sure that there is nothing in the response except the JSON. "
    }
}