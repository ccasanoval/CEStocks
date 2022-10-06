package com.cesoft.cestocks.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoadingIndicator(color: Color, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            color = color,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(group="Test")
@Composable
fun LoadingIndicator_Preview() {
    LoadingIndicator(color = Color.White, modifier = Modifier.fillMaxSize())
}
