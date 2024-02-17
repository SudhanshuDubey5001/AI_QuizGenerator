package com.sudhanshu.quizapp.feature_quiz.presentation.topics

sealed class TopicsScreenEvents {

    data class OnTopicInputEntered(val value: String) : TopicsScreenEvents()

    object OnSubmitTopic: TopicsScreenEvents()

    data class OnTopicDeletePressed(val topicListIndex: Int): TopicsScreenEvents()

    data class OnTopicSelectedFromPopularTopics(val topicState: PopularTopicState): TopicsScreenEvents()
}