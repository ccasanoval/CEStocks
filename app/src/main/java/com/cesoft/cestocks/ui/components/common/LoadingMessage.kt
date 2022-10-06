package com.cesoft.cestocks.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingMessage(
    message: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface
) {
    Box(modifier = modifier) {
        Text(
            text = message,
            style = MaterialTheme.typography.h5,
            color = color,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(group="Test")
@Composable
private fun LoadingMessage_Preview() {
    LoadingMessage("Loading data...")
}
