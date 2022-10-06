package com.cesoft.cestocks.ui.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cesoft.cestocks.R

@Composable
fun Splash(modifier: Modifier = Modifier) {//TODO: Rename
    Column(modifier = modifier) {
        val image: Painter = painterResource(id = R.drawable.splash)
        Image(
            painter = image, contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
    Column(modifier = modifier) {
        LoadingIndicator(
            color = Color.White,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LoadingMessage(
            message = stringResource(R.string.loading),
            modifier = Modifier.wrapContentSize(),
            color = Color.White
        )
    }
}

@Preview(group="Test")
@Composable
private fun DownloadingMessage_Preview() {
    Splash(modifier = Modifier.wrapContentSize(Alignment.Center))
}
