package com.sudhanshu.quizapp.feature_quiz.presentation.options.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudhanshu.quizapp.core.presentation.components.QuizAppNavigationBar
import com.sudhanshu.quizapp.core.utils.Screens
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.domain.model.Level
import com.sudhanshu.quizapp.feature_quiz.presentation.options.OptionScreenEvents
import com.sudhanshu.quizapp.feature_quiz.presentation.options.OptionScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionScreen(
    navController: NavController,
    viewModel: OptionScreenVM = hiltViewModel()
) {

    val config = viewModel.config.value
    val focus = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    val showQuestionsCounter = remember { mutableStateOf(false) }

    fun onBackButtonPressed() {
        navController.popBackStack()
    }

    fun onStartPressed() {
        navController.navigate(Screens.LOADING)
    }

    Scaffold(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                focus.clearFocus()
            }
    ) {
        Modifier.padding(it)
        Column {
            QuizAppNavigationBar(heading = "Options", onClickBackButton = { onBackButtonPressed() })

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(20.dp))

                CardCustom() {
                    CardHeading(heading = "Name")
                    NameInput(config = config, onValueChanged = { name ->
                        viewModel.onEvents(OptionScreenEvents.OnValueChangedNameInput(name))
                    })
                }

                SpacerCustom()

                CardCustom {
                    CardHeading(heading = "Questions")
                    QuestionsComponentView(
                        count = config.questionsCount,
                        onPressQuestionCounter = {
                            showQuestionsCounter.value = true
                        }
                    )
                    SpacerCustom()

                    Slider(
                        value = config.questionsCount.toFloat(),
                        onValueChange = { value ->
                            viewModel.onEvents(OptionScreenEvents.OnValueChangedQuestionsCount(value))
                        },
                        valueRange = 5f..50f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.scrim,
                            activeTrackColor = MaterialTheme.colorScheme.scrim
                        )
                    )
                    SpacerCustom()
                }
                SpacerCustom()

                CardCustom {
                    CardHeading(heading = "Difficulty Level")
                    DifficultyLevelComponentView(
                        selectionChange = { value ->
                            var selection: Level = Level.EASY
                            when (value) {
                                0 -> selection = Level.EASY
                                1 -> selection = Level.MEDIUM
                                2 -> selection = Level.HARD
                            }
                            viewModel.onEvents(OptionScreenEvents.OnDifficultyLevelChanged(selection))
                        }
                    )
                    SpacerCustom()
                }
                SpacerCustom()

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                    onClick = { onStartPressed() }) {
                    Text(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        text = "Start",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                }
            }
        }

    }
}

@Composable
fun SpacerCustom() {
    Spacer(modifier = Modifier.height(20.dp))
}
