package com.cesoft.cestocks.ui.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cesoft.cestocks.R

@Composable
fun ToolbarWindow(
    title: String = "",
    onBack: (() -> Unit)? = null,
    topBar: @Composable () -> Unit = {},
    contentBody: @Composable (padding: PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar {
                onBack?.let {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colors.secondary
                        )
                    }
                } ?: run {
                    val image: Painter = painterResource(R.mipmap.ic_launcher)
                    Icon(image, "", tint= Color.Unspecified)
                }
                Text(
                    text = title,
                    fontSize = 24.sp
                )
                topBar()
            }
        }
    ) { padding ->
        Column {
            Row {
                contentBody(padding)
            }
        }
    }
}

//------------------------------------------------------------------------------------------------
@Preview(group = "Test")
@Composable
private fun ToolbarWindow_Preview() {
    ToolbarWindow(
        title = "Título",
        onBack = {},
        topBar = { Text("AAA") },
        contentBody = { Text("Content Body") }
    )
}

@Preview(group = "Test")
@Composable
private fun ToolbarWindow_Preview2() {
    ToolbarWindow(
        title = "Título",
        //onBack = {},
        topBar = {},
        contentBody = {}
    )
}