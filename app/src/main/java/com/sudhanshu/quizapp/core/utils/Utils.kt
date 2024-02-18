package com.sudhanshu.quizapp.core.utils

import android.util.Log
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.sudhanshu.quizapp.R

object Utils {
    fun log(s: String) {
        Log.d("myLog", s)
    }

    val fontFamily = FontFamily(
        Font(
            R.font.comfortaa, FontWeight.Normal
        )
    )
}