package com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sudhanshu.quizapp.R
import com.sudhanshu.quizapp.core.presentation.components.GifView
import com.sudhanshu.quizapp.feature_quiz.presentation.quiz.LoadingState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableQuestionsView(
    loading: LoadingState,
    pagerState: PagerState,
    onRetry: () -> Unit
) {
    val page = pagerState.currentPage
    val bubbleSize = 34.dp
    val scope = rememberCoroutineScope()

    fun goToPage(page: Int) {
        scope.launch {
            pagerState.scrollToPage(page)
        }
    }

    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val colorBubble =
                if (page == iteration) MaterialTheme.colorScheme.onPrimary
                else Color.DarkGray
            val colorBubbleContent =
                if (page == iteration) Color.Black
                else Color.White
            Box(
                modifier = Modifier
                    .offset(x = (pagerState.currentPageOffsetFraction * 0.5f).dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(colorBubble)
                    .size(bubbleSize)
                    .clickable { goToPage(iteration) }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = (iteration + 1).toString(),
                    color = colorBubbleContent
                )
            }
        }
        if (loading == LoadingState.LOADING) {
            GifView(
                data = R.drawable.loadinggif,
                Modifier
                    .align(Alignment.CenterVertically)
                    .size(54.dp)
            )
        } else if (loading == LoadingState.ERROR) {
            Icon(
                modifier = Modifier
                    .size(54.dp)
                    .clickable { onRetry() },
                imageVector = Icons.Default.Refresh,
                contentDescription = "retry loading questions"
            )
        }
    }
}