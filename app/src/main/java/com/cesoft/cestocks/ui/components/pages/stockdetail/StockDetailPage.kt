package com.cesoft.cestocks.ui.components.pages.stockdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cesoft.cestocks.R
import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Quote
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.entities.StockHistory
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.common.fullName
import com.cesoft.cestocks.ui.components.common.ErrorMessage
import com.cesoft.cestocks.ui.components.common.LoadingIndicator
import com.cesoft.cestocks.ui.components.common.ToolbarWindow
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

@Composable
fun StockDetailPage(
    state: StockDetailState,
    onRefresh: () -> Unit,
    onBack: () -> Unit,
) {
    Window(
        stock = state.stockHistory?.stock,
        onRefresh=onRefresh,
        onBack=onBack
    ) {
        when(state.status) {
            UiStatus.Loading -> {
                LoadingCompo()
            }
            is UiStatus.Failed -> {
                FailedCompo(state.status.message)
            }
            UiStatus.Success -> {
                SuccessCompo(state.stockHistory!!)
            }
            else -> {}
        }
    }
}

@Composable
fun Window(
    stock: Stock?,
    onRefresh: () -> Unit,
    onBack: () -> Unit,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    ToolbarWindow(
        title = stock?.fullName() ?: stringResource(R.string.stock_detail_page),
        onBack = onBack,
        topBar = {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = onRefresh
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        stringResource(R.string.refresh),
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
        },
        contentBody = content
    )
}

@Composable
fun LoadingCompo() {
    LoadingIndicator(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxSize())
}

@Composable
fun FailedCompo(message: String) {
    ErrorMessage(message = "Error: $message")
}

@Composable
fun SuccessCompo(stockHistory: StockHistory) {
    //https://proandroiddev.com/creating-graph-in-jetpack-compose-312957b11b2
    val stock = stockHistory.stock
    val value = stockHistory.value

    Column {
        value.forEach { price ->
            Column {
                Text(text = "${price.date} ----- ${price.close} ${stock.market.currency}")
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
    val value = mutableListOf<Quote>()
    val day = 24*3600*1000L
    val now = Date().time - n*day
    for(i in 0..n) {
        value.add(Quote(
            date = Date(now + i*day),
            close = (Random().nextDouble()*(i+10)).toBigDecimal(),
            open = BigDecimal(0),
            low = BigDecimal(0),
            high = BigDecimal(0),
            volume = BigInteger("0")
        ))
    }
    val stockHistory = StockHistory(stock, value)
    val state = StockDetailState(status=UiStatus.Success, stockHistory=stockHistory)
    StockDetailPage(state, {}, {})
}