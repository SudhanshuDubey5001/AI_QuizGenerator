package com.sudhanshu.quizapp.feature_quiz.presentation.loading.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudhanshu.quizapp.core.presentation.UiEvent
import com.sudhanshu.quizapp.core.presentation.components.SetStatusBarColor
import com.sudhanshu.quizapp.core.utils.Utils
import com.sudhanshu.quizapp.feature_quiz.presentation.loading.LoadingScreenEvents
import com.sudhanshu.quizapp.feature_quiz.presentation.loading.LoadingScreenVM
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(
    onNavigate: (route: String) -> Unit,
    viewModel: LoadingScreenVM = hiltViewModel()
) {

    val error = viewModel.error.value
    val retrySafetyCount = viewModel.retryCountSafety.value

    LaunchedEffect(Unit){
        viewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.navigate -> {
                    onNavigate(event.screen)
                }
                is UiEvent.showSnackBar -> Unit
            }
        }
    }

    SetStatusBarColor(color = MaterialTheme.colorScheme.primary)

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Modifier.padding(it)
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 40.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "AI is creating your quiz",
                    fontSize = 42.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 50.sp,
                    fontFamily = Utils.fontFamily
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier
                    .padding(vertical = 40.dp, horizontal = 0.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!error) CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .size(20.dp),
                    color = Color.White
                )
                else {
                    ErrorView(
                        retrySafetyCount = retrySafetyCount,
                        onRetryClick = {
                            viewModel.onEvents(LoadingScreenEvents.retryGeneratingQuiz)
                        }
                    )
                }
            }
        }
    }
}