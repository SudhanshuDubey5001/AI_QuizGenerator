package com.sudhanshu.quizapp.feature_quiz.presentation.topics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.PromptBlockedException
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.core.utils.Prompts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicScreenVM @Inject constructor(
    private val generativeModel: GenerativeModel
) : ViewModel() {

    private val _topicProps = mutableStateOf(TopicState())
    val topicProps: State<TopicState> = _topicProps

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    private val _topics = mutableStateOf(emptyList<String>())
    val topics = _topics

    private val DEBOUNCE_TIME_MS = 2000L

    var generativeAIJob: Job = Job()

    private fun onSubmitTopic() {
        Utils.log("Submit called")
        if (_topicProps.value.name.isBlank()) return
        Utils.log("Not blank")
        if (_topicProps.value.loading) {
            Utils.log("Still loading, showing snackbar...")
            showSnackBar("Just a second, validating if AI is happy with your topic")
            return
        }
        Utils.log("After snackbar")
        if (!_topicProps.value.isValid) return
        Utils.log("After isValid")
        resetTopicState()
    }

    private fun validateTopic(topic: String) {
        Utils.log("TOPIC == " + topic)
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

    suspend fun callGeminiAPI(prompt: String): String {
        val response = generativeModel.generateContent(prompt = prompt)
        return response.text.toString()
    }

    private fun showSnackBar(msg: String) {
        viewModelScope.launch {
            _uiEvents.emit(
                UiEvent.showSnackBar(msg)
            )
        }
    }

    fun onEvents(event: TopicsScreenEvents) {
        when (event) {
            is TopicsScreenEvents.onTopicInputEntered -> {
                //first cancel the old gemini API request if still running
                _topicProps.value = _topicProps.value.copy(
                    name = event.value,
                    loading = true
                )
                cancelPreviousAPIRequests()
                if (event.value.isNotBlank()) validateTopic(event.value)
            }

            TopicsScreenEvents.onSubmitTopic -> {
                onSubmitTopic()
            }
        }
    }
}