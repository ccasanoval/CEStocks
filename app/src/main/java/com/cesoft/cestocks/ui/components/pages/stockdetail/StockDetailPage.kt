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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.entities.StockHistory
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.common.fullTicket
import com.cesoft.cestocks.ui.components.dlg.ErrorMessage
import com.cesoft.cestocks.ui.components.dlg.LoadingIndicator
import java.math.BigDecimal
import java.util.*

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
    //https://proandroiddev.com/creating-graph-in-jetpack-compose-312957b11b2
    val stock = stockHistory.stock
    val prices = stockHistory.prices
    val dates = stockHistory.dates

    Column {
        Text(text = "Detalles de ${stock.fullTicket()}")
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${stock.name} (${stock.fullTicket()})",
            modifier = Modifier.size(24.dp)
        )
        prices.forEach { price ->
            Column {
                Text(text = "----- $price $")
            }
        }
    }
}


@Preview(group = "Test")
@Composable
fun StockDetailPage_Preview() {
    val market = Market(id=0, name="Bolsa de Madrid", ticker="MC", currency="€")
    val stock = Stock(id=0, name="Santander", ticker="SAN", market=market)

    val n = 100
    val dates = mutableListOf<Date>()
    val prices = mutableListOf<BigDecimal>()
    val day = 24*3600*1000L
    val now = Date().time - n*day
    for(i in 0..n) {
        dates.add(Date(now + i*day))
        prices.add((Random().nextDouble()*(i+10)).toBigDecimal())
    }
    val stockHistory = StockHistory(stock, dates, prices)
    val state = StockDetailState(status=UiStatus.Success, stockHistory=stockHistory)
    StockDetailPage(state) {}
}