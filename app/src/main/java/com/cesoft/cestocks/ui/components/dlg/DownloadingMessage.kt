package com.cesoft.cestocks.ui.components.dlg

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DownloadingMessage(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        LoadingIndicator(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LoadingMessage(
            message = "Downloading",
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Preview(group="Test")
@Composable
private fun DownloadingMessage_Preview() {
    DownloadingMessage(modifier = Modifier.wrapContentSize(Alignment.Center))
}
