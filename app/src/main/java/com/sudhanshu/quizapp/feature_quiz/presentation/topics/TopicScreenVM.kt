package com.sudhanshu.quizapp.feature_quiz.presentation.topics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.PromptBlockedException
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.core.utils.Prompts
import com.sudhanshu.quizapp.feature_quiz.data.data_source.GlobalData
import com.sudhanshu.quizapp.feature_quiz.data.data_source.PopularTopics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicScreenVM @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val globalData: GlobalData
) : ViewModel() {

    private val _topicProps = mutableStateOf(TopicState())
    val topicProps: State<TopicState> = _topicProps

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    val topicsSelected = globalData.topicsSelected

    private val _popularTopicsStateHolder = mutableStateListOf<PopularTopicState>()
    val popularTopicsStateHolder: SnapshotStateList<PopularTopicState> = _popularTopicsStateHolder

    private val DEBOUNCE_TIME_MS = 2000L

    var generativeAIJob: Job = Job()

    init {
        PopularTopics.topics.forEach { topic ->
            _popularTopicsStateHolder.add(
                PopularTopicState(
                    topic = topic
                )
            )
        }
    }

    private fun onSubmitTopic() {
        if (_topicProps.value.name.isBlank()) return
        if (_topicProps.value.loading) {
            showSnackBar("Just a second, validating if AI is happy with your topic")
            return
        }
        if (!_topicProps.value.isValid) return
        globalData.addTopic(_topicProps.value.name)
        updatePopularTopicState(_topicProps.value.name)
        resetTopicState()
    }

    private fun validateTopic(topic: String) {
//        val exists = globalData.checkIfExists(_topicProps.value.name)
        if (globalData.topicsSelected.contains(_topicProps.value.name)) {
            _topicProps.value = _topicProps.value.copy(
                loading = false,
                isAlreadyExistInList = true
            )
            return
        }
        val prompt = Prompts.validatePrompt(topic)
        generativeAIJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME_MS)
            try {
                Utils.log("------Calling Gemini API------")
                val response = callGeminiAPI(prompt)
                Utils.log(response)
                _topicProps.value = _topicProps.value.copy(
                    isValid = response.lowercase() == "yes",
                    loading = false
                )
            } catch (e: PromptBlockedException) {
                //when user uses obscene language or something offensive or generating a quiz is not possible
                _topicProps.value = _topicProps.value.copy(
                    isValid = false,
                    loading = false
                )
            } catch (e: Exception) {
                _topicProps.value = _topicProps.value.copy(
                    isValid = false,
                    loading = false
                )
            }
        }
    }

    private fun resetTopicState() {
        _topicProps.value = TopicState()
    }

    private fun cancelPreviousAPIRequests() {
        if (generativeAIJob.isActive) generativeAIJob.cancel()
    }

    private fun showSnackBar(msg: String) {
        viewModelScope.launch {
            _uiEvents.emit(
                UiEvent.showSnackBar(msg)
            )
        }
    }

    private fun deleteTopicFromList(index: Int) {
        _popularTopicsStateHolder.forEachIndexed { i, topicState ->
            if (topicState.topic == globalData.topicsSelected[index]) {
                _popularTopicsStateHolder[i] = PopularTopicState(
                    topic = topicState.topic,
                    isSelected = false
                )
            }
        }
        globalData.removeTopic(index)
    }

    private fun addTopicFromPopularTopics(topicState: PopularTopicState) {
        globalData.addTopic(topicState.topic)
        updatePopularTopicState(topicState.topic)
    }

    private fun updatePopularTopicState(topic:String){
        _popularTopicsStateHolder.forEachIndexed { index, topicState ->
            if(topicState.topic == topic){
                _popularTopicsStateHolder[index] = PopularTopicState(
                    topic = topicState.topic,
                    isSelected = !topicState.isSelected
                )
            }
        }
    }

    fun onEvents(event: TopicsScreenEvents) {
        when (event) {
            is TopicsScreenEvents.OnTopicInputEntered -> {
                val topic = event.value.lowercase()
                _topicProps.value = _topicProps.value.copy(
                    name = topic,
                    loading = true,
                    isAlreadyExistInList = false
                )
                //first cancel the old gemini API request if still running
                cancelPreviousAPIRequests()
                if (event.value.isNotBlank()) validateTopic(topic)
                else resetTopicState()
            }

            TopicsScreenEvents.OnSubmitTopic -> {
                _topicProps.value = _topicProps.value.copy(
                    isSubmitted = TopicSubmittedState.PROCESSING
                )
                onSubmitTopic()
            }

            is TopicsScreenEvents.OnTopicDeletePressed -> {
                deleteTopicFromList(event.topicListIndex)
            }

            is TopicsScreenEvents.OnTopicSelectedFromPopularTopics -> {
                addTopicFromPopularTopics(event.topicState)
            }
        }
    }
    private suspend fun callGeminiAPI(prompt: String): String {
        val response = generativeModel.generateContent(prompt = prompt)
        return response.text.toString()
    }
}