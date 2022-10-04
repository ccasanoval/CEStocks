package com.cesoft.cestocks.ui.components.pages.addstock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.components.dlg.DownloadRetryMessage
import com.cesoft.cestocks.ui.components.dlg.DownloadingMessage

@Composable
fun AddStockPage(
    state: AddStockState
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when(state.status) {
                UiStatus.Loading -> {
                    DownloadingMessage(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    )
                }
                is UiStatus.Failed -> {
                    DownloadRetryMessage(
                        onRetry = { },
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    )
                }
                UiStatus.Success -> {
                    Text("Add new Quote")
                }
                null -> Unit
            }
        }
    }
}

@Preview(group = "Test")
@Composable
private fun InitPage_Preview() {
    AddStockPage(AddStockState(UiStatus.Success))
}