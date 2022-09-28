package com.cesoft.cestocks.ui.components.dlg

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cesoft.cestocks.R

@Composable
fun RetryButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Button(
        onClick = { onClick?.invoke() },
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.retry))
    }
}

@Preview(group="Test")
@Composable
private fun RetryButton_Preview() {
    RetryButton(
        modifier = Modifier.wrapContentSize()
    )
}
