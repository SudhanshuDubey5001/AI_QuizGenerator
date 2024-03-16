package com.sudhanshu.quizapp.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.NavigationBar

@Composable
fun QuizAppNavigationBar(
    heading: String = "",
    trailingText: String = "",
    onClickBackButton: () -> Unit,
    onClickTrailingText: (() -> Unit)? = null
) {
    SetStatusBarColor(color = MaterialTheme.colorScheme.primary)
    NavigationBar(
        modifier = Modifier.height(56.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onClickBackButton) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back button"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = heading,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clickable { onClickTrailingText?.invoke() },
                text = trailingText,
                fontSize = 20.sp
            )
        }
    }
}