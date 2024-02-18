package com.sudhanshu.quizapp.core.presentation

import com.sudhanshu.quizapp.core.utils.Screens

sealed class UiEvent {
    data class showSnackBar(val msg: String): UiEvent()

    data class navigate(val screen: String): UiEvent()
}