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
fun DownloadRetryMessage(onRetry: (() -> Unit)? = null, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ErrorMessage(
            message = "Download Failed",
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        RetryButton(
            onClick = onRetry,
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(group="Test")
@Composable
private fun DownloadRetryMessage_Preview() {
    DownloadRetryMessage(modifier = Modifier.wrapContentSize(Alignment.Center))
}
