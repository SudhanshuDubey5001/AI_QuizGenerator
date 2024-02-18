package com.sudhanshu.quizapp.feature_quiz.presentation.options

import com.sudhanshu.quizapp.feature_quiz.domain.model.Level

sealed class OptionScreenEvents {
    data class OnValueChangedNameInput(val name: String): OptionScreenEvents()

    data class OnValueChangedQuestionsCount(val count: Float): OptionScreenEvents()

    data class OnDifficultyLevelChanged(val level: Level): OptionScreenEvents()
}