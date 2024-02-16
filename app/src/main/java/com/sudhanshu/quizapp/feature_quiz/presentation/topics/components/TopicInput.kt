package com.sudhanshu.quizapp.feature_quiz.presentation.topics.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.R
import com.sudhanshu.quizapp.feature_quiz.presentation.topics.TopicState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicInput(
    fontFamily: FontFamily,
    inputProp: TopicState,
    onValueChanged: (newValue: String) -> Unit,
    onSubmitTopic: () -> Unit
) {

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Which topic?",
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = fontFamily
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Type any topic you like")
            },
            value = inputProp.name,
            onValueChange = { newValue -> onValueChanged(newValue) },
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontFamily = fontFamily
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onSubmitTopic() }),
            trailingIcon = {
                if (inputProp.name.isNotEmpty()) {
                    if (inputProp.loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        if (inputProp.isValid) {
                            Image(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color.Transparent),
                                painter = painterResource(R.drawable.validated_icon),
                                contentDescription = "green tick icon | validated"
                            )
                        } else {
                            Image(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(Color.Transparent),
                                painter = painterResource(R.drawable.not_validated_icon),
                                contentDescription = "green tick icon | validated"
                            )
                        }
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (inputProp.name.isNotEmpty()) {
            if (!inputProp.loading) {
                if (!inputProp.isValid) ErrorMessageDisplay()
            }
        }
    }
}