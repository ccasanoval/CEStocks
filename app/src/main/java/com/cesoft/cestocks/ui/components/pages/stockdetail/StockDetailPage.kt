package com.cesoft.cestocks.ui.components.pages.stockdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cesoft.cestocks.domain.entities.StockHistory
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.common.fullTicket
import com.cesoft.cestocks.ui.components.dlg.ErrorMessage
import com.cesoft.cestocks.ui.components.dlg.LoadingIndicator

@Composable
fun StockDetailPage(
    state: StockDetailState,
    onBack: () -> Unit
) {
    when (state.status) {
        UiStatus.Loading -> {
            LoadingIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxSize()
            )
        }
        is UiStatus.Failed -> {
            ErrorMessage(message = state.status.message)
        }
        UiStatus.Success -> {
            SuccessCompo(state.stockHistory!!)
        }
        else -> {

        }
    }
}

@Composable
fun SuccessCompo(stockHistory: StockHistory) {
    val stock = stockHistory.stock
    val prices = stockHistory.prices
    val dates = stockHistory.dates

    Column {
        Text(text = "Detalles de $stock")
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${stock.name} (${stock.fullTicket()})",
            modifier = Modifier.size(24.dp)
        )
        prices.forEach { price ->
            Column {
                Text(text = "$price $")
            }
        }
    }
}