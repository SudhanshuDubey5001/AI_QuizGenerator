package com.sudhanshu.quizapp.feature_quiz.presentation.quiz.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sudhanshu.quizapp.feature_quiz.domain.model.Question
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableQuestionsView(
    quizData: SnapshotStateList<Question>,
    questionsPagerState: PagerState,
) {
    val questionsPageIndex = questionsPagerState.currentPage

    val bubbleSizeNormal = 34.dp
    val bubbleSizeActive = 42.dp
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    //to check if user is scrolling or not
    val userScrollState = remember { mutableStateOf(false) }

    val indexList: MutableList<Int> = (0 until quizData.size).toMutableList()

    fun goToPage(page: Int) {
        scope.launch {
            questionsPagerState.scrollToPage(page)
        }
    }

    fun scrollItems(page: Int) {
        scope.launch {
            scrollState.animateScrollToItem(page)
        }
    }

    LaunchedEffect(questionsPageIndex) {
        //Pager change item listener
        userScrollState.value = false
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        state = scrollState,
    ) {
        items(indexList) { index ->
            if (scrollState.isScrollInProgress) userScrollState.value = true

            if (questionsPageIndex > 2 && !userScrollState.value) {
                scrollItems(questionsPageIndex - 3)
            }

            var colorBubble: Color
            var colorBubbleContent: Color

            if (quizData[index].questionAttempted && quizData[index].isCorrect) {
                colorBubble = MaterialTheme.colorScheme.outline
                colorBubbleContent = Color.White
            } else if (quizData[index].questionAttempted && !quizData[index].isCorrect) {
                colorBubble = MaterialTheme.colorScheme.error
                colorBubbleContent = Color.White
            } else {
                colorBubble = Color.DarkGray
                colorBubbleContent = Color.White
            }

            //current page indicator
            if (questionsPageIndex == index) {
                colorBubble = Color.White
                colorBubbleContent = Color.Black
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(colorBubble)
                    .size(if (questionsPageIndex == index) bubbleSizeActive else bubbleSizeNormal)
                    .clickable { goToPage(index) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = (index + 1).toString(),
                    color = colorBubbleContent
                )
            }
        }
    }
}
