package com.sudhanshu.quizapp.feature_quiz.presentation.result.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.sudhanshu.quizapp.core.presentation.components.QuizAppNavigationBar
import com.sudhanshu.quizapp.core.utils.AppConfigurationConstants
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.presentation.result.Grade
import com.sudhanshu.quizapp.feature_quiz.presentation.result.ResultScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    onNavigate: (route: String) -> Unit,
    viewModel: ResultScreenVM = hiltViewModel()
) {

    val resultState = viewModel.resultState.value

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    fun openURL(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Modifier.padding(it)
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            QuizAppNavigationBar(
                heading = "",
                onClickBackButton = {
//                    onNavigate(Screens.BACK)
                })

            var performanceOfUser = ""
            when (resultState.grade) {
                Grade.VERY_BAD -> performanceOfUser = AppConfigurationConstants.RESULT_MSG_VERY_BAD
                Grade.BAD -> performanceOfUser = AppConfigurationConstants.RESULT_MSG_BAD
                Grade.FINE -> performanceOfUser = AppConfigurationConstants.RESULT_MSG_FINE
                Grade.GOOD -> performanceOfUser = AppConfigurationConstants.RESULT_MSG_GOOD
                Grade.VERY_GOOD -> performanceOfUser =
                    AppConfigurationConstants.RESULT_MSG_VERY_GOOD

                Grade.EXCELLENT -> performanceOfUser =
                    AppConfigurationConstants.RESULT_MSG_EXCELLENT
            }

            if (resultState.scorePercentage == 1f) performanceOfUser =
                AppConfigurationConstants.RESULT_MSG_PERFECT_SCORE

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 30.dp),
                    text = performanceOfUser,
                    fontFamily = Utils.fontFamily,
                    fontSize = 32.sp,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressBarWithResult(
                        percentage = resultState.scorePercentage,
                        content = resultState.scoreText,
                        fontSize = 32.sp,
                        color = resultState.progressIndicatorColor
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                val pieChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice(
                            "SciFi",
                            (resultState.scorePercentage * 100),
                            AppConfigurationConstants.PIE_CHART_CORRECT_COLOR
                        ),
                        PieChartData.Slice(
                            "Romance",
                            (100 - (resultState.scorePercentage * 100)),
                            AppConfigurationConstants.PIE_CHART_INCORRECT_COLOR
                        )
                    ),
                    plotType = PlotType.Pie
                )

                val pieChartConfig = PieChartConfig(
                    isAnimationEnable = true,
                    showSliceLabels = false,
                    animationDuration = 1500,
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(10.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        PieChart(
                            modifier = Modifier,
                            pieChartData,
                            pieChartConfig
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Correct",
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Card(
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = AppConfigurationConstants.PIE_CHART_CORRECT_COLOR
                                    )
                                ) {}
                            }
                            Row(
                                modifier = Modifier.weight(1f),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Incorrect",
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Card(
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = AppConfigurationConstants.PIE_CHART_INCORRECT_COLOR
                                    )
                                ) {}
                            }
                        }
                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = "Resources",
                            fontSize = 28.sp,
                            fontFamily = Utils.fontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        viewModel.resources.forEach { resource ->
                            Text(
                                modifier = Modifier.clickable {
                                    openURL(resource.url)
                                },
                                text = resource.url,
                                fontSize = 18.sp,
                                color = Color.Blue,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = resource.explanation,
                                fontSize = 18.sp,
                                fontFamily = Utils.fontFamily,
                                color = Color.Black,
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

//@Preview
//@Composable
//fun preview() {
//    ResultScreen()
//}