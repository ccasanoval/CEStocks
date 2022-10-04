package com.cesoft.cestocks.ui.components.pages.init

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.components.dlg.DownloadRetryMessage
import com.cesoft.cestocks.ui.components.dlg.DownloadingMessage

@Composable
fun InitPage(
    state: InitState,
    onRetry: () -> Unit
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            DownloadingMessage(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
//            when(state.status) {
//                UiStatus.Loading -> {
//                    DownloadingMessage(
//                        modifier = Modifier
//                            .wrapContentSize()
//                            .align(Alignment.Center)
//                    )
//                }
//                is UiStatus.Failed -> {
//                    DownloadRetryMessage(
//                        onRetry = { onRetry() },
//                        modifier = Modifier
//                            .wrapContentSize()
//                            .align(Alignment.Center)
//                    )
//                }
//                UiStatus.Success -> {
//                }
//                null -> Unit
//            }
        }
    }
}

@Preview(group = "Test")
@Composable
private fun InitPage_Preview() {
    InitPage(InitState(UiStatus.Success)) {}
}