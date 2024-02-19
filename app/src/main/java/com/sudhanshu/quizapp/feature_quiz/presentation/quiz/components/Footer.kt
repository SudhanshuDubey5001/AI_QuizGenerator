package com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudhanshu.quizapp.core.utils.Utils
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Footer(
    pagerState: PagerState
) {
    val sizeOfArrows = 42.dp
    val scope = rememberCoroutineScope()

    fun goToNextPage() {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage + 1)
        }
    }
    fun goToPreviousPage() {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage - 1)
        }
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .size(sizeOfArrows)
                .clickable { goToPreviousPage() },
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "previous question"
        )

        Card(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            elevation = CardDefaults.cardElevation(10.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 16.dp),
                text = "Next",
                fontFamily = Utils.fontFamily,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }

        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .size(sizeOfArrows)
                .clickable { goToNextPage() },
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "previous question"
        )
    }
}