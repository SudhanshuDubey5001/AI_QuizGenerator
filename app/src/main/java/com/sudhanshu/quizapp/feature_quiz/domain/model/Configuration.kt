package com.sudhanshu.quizapp.feature_quiz.domain.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Configuration(
    val topics: List<String>,
    val level: Level = Level.EASY,
    val questionsCount: Int = 10,
)

enum class Level{
    EASY, MEDIUM, HARD
}