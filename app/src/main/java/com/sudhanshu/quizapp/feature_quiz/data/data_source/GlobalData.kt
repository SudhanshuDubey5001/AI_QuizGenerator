package com.sudhanshu.quizapp.feature_quiz.data.data_source

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalData @Inject constructor() {

    private val _topicsSelected = mutableStateListOf<String>()
    val topicsSelected: SnapshotStateList<String> = _topicsSelected

    fun addTopic(topic: String) {
        if(!topicsSelected.contains(topic)){
            _topicsSelected.add(topic)
        }
    }

    fun removeTopic(index: Int) {
        _topicsSelected.removeAt(index)
    }
}