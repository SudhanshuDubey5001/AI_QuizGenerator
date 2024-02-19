package com.sudhanshu.quizapp.feature_quiz.data.data_source

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.sudhanshu.quizapp.feature_quiz.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserData @Inject constructor() {

    private val _answers = mutableStateListOf<Int>()

    private val _user = mutableStateOf(
        User(answers = _answers)
    )
    val user: State<User> = _user

    fun setName(name: String) {
        _user.value = _user.value.copy(
            name = name
        )
    }

    fun addUserAnswer(value: Int) {
        _answers.add(value)
    }

    fun setScore(score: Int) {
        _user.value = _user.value.copy(
            totalScore = _user.value.totalScore + score
        )
    }
}