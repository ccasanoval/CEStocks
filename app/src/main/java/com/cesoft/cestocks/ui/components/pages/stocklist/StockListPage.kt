package com.cesoft.cestocks.ui.components.pages.stocklist

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StockListPage(
    state: StockListState,
) {
    Column {
        state.stockList.forEach {
            Text(text = it.name)
        }
    }
}