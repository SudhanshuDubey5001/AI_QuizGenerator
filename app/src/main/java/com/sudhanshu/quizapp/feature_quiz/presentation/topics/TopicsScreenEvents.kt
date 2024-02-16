package com.sudhanshu.quizapp.feature_quiz.presentation.topics

sealed class TopicsScreenEvents {

    data class onTopicInputEntered(val value: String) : TopicsScreenEvents()

    object onSubmitTopic: TopicsScreenEvents()
}