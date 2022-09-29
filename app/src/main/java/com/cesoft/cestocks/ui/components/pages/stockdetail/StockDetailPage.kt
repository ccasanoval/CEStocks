package com.cesoft.cestocks.ui.components.pages.stockdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StockDetailPage(
    state: StockDetailState,
    onBack: () -> Unit
) {
    Text(text = "Detalles de ${state.stockHistory?.stock}")
    state.stockHistory?.let { h ->
        Text(
            text = "${h.stock.name} (${h.stock.ticker}.${h.stock.market.ticket})",
            modifier = Modifier.size(24.dp)
        )
        h.prices.forEach { price ->
            Column {
                Text(text = "$price €")
            }
        }
    }
}