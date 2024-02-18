package com.sudhanshu.quizapp.feature_quiz.data.data_source

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.sudhanshu.quizapp.feature_quiz.domain.model.Configuration
import com.sudhanshu.quizapp.feature_quiz.domain.model.Level
import com.sudhanshu.quizapp.feature_quiz.domain.model.Quiz
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalData @Inject constructor() {

    private val _topicsSelected = mutableStateListOf<String>()
    private val _config = mutableStateOf(
        Configuration(
            topics = _topicsSelected
        )
    )
    val configuration: State<Configuration> = _config

    private val _quizData = mutableStateOf(Quiz())
    val quizData: State<Quiz> = _quizData

    fun setQuizData(data: Quiz){
        _quizData.value = data
    }

    fun addTopic(topic: String) {
        if (!configuration.value.topics.contains(topic)) {
            _topicsSelected.add(topic)
        }
    }

    fun removeTopic(index: Int) {
        _topicsSelected.removeAt(index)
    }

    fun setName(name: String) {
        _config.value = _config.value.copy(
            name = name
        )
    }

    fun setDifficultyLevel(level: Level) {
        _config.value = _config.value.copy(
            level = level
        )
    }

    fun setQuestionCount(count: Int) {
        _config.value = _config.value.copy(
            questionsCount = count
        )
    }
}