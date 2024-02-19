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
    fun extractJson(inputString: String): String {
        // Find the starting and ending index of the JSON object
        val startIndex = inputString.indexOf("{")
        val endIndex = inputString.lastIndexOf("}")

        return if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            // Extract the JSON substring
            inputString.substring(startIndex, endIndex + 1)
        } else {
            // JSON object not found
            // TODO: Do it again, 3 times then show error page
            inputString
        }
    }
}