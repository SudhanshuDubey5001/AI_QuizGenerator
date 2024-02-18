package com.sudhanshu.quizapp.feature_quiz.presentation.topics

data class Topic(
    val name: String = "",
    val topicState: TopicState = TopicState.IDLE,
//    val isSubmitted: TopicSubmittedState = TopicSubmittedState.NOT_ADDED,
    val isSubmitted: Boolean = false,
)

enum class TopicSubmittedState {
    NOT_ADDED, PROCESSING, ADDED
}

enum class TopicState {
    IDLE,
    LOADING,
    VALID,
    INVALID,
    EXIST_IN_LIST,
}