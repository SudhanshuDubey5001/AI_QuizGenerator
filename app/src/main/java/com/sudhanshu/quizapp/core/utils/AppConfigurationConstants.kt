package com.sudhanshu.quizapp.core.utils

import androidx.compose.ui.graphics.Color

object AppConfigurationConstants {
    const val ENTRY_SCREEN = Screens.WELCOME
    // number of times user is allowed to press retry
    const val GENERATIVE_AI_API_CALL_RETRY_LIMIT = 3
    const val QUESTIONS_COUNT_PER_API_CALL = 5  // IMPORTANT: Changing this value might affect the JSON response!!
}