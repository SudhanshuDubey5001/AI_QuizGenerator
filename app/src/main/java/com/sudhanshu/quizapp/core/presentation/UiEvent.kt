package com.sudhanshu.quizapp.core.presentation

sealed class UiEvent {
    data class showSnackBar(val msg: String): UiEvent()
}