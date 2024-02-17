package com.sudhanshu.quizapp.feature_quiz.presentation.topics

data class TopicState(
    val name: String = "",
    val isValid: Boolean = false,
    val loading: Boolean = false,
    val isSubmitted: TopicSubmittedState = TopicSubmittedState.NOT_ADDED,
    val isAlreadyExistInList: Boolean = false
)

enum class TopicSubmittedState {
    NOT_ADDED, PROCESSING, ADDED
}