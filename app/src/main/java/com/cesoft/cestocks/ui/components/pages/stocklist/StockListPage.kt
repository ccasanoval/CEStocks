package com.cesoft.cestocks.ui.components.pages.stocklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesoft.cestocks.R
import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.common.fullTicket
import com.cesoft.cestocks.ui.components.OnLifecycleEvent
import com.cesoft.cestocks.ui.components.common.ErrorMessage
import com.cesoft.cestocks.ui.components.common.LoadingIndicator
import com.cesoft.cestocks.ui.components.common.ToolbarWindow

@Composable
fun StockListPage(
    state: StockListState,
    onStockClick: (Stock) -> Unit,
    onAddStock: () -> Unit,
    onRefresh: () -> Unit
) {
    OnLifecycleEvent { _, event ->
        //android.util.Log.e("AA", "--------- on lifecycle $event")
        //if(event == Lifecycle.Event.ON_START) onRefresh()
    }
    DisposableEffect(null) {
        onRefresh()
        //android.util.Log.e("AA", "--------- onStart")
        onDispose {
            //android.util.Log.e("AA", "--------- onDispose")
        }
    }

    Window(onAddStock=onAddStock, onRefresh=onRefresh) {
        when(state.status) {
            UiStatus.Loading -> {
                LoadingCompo()
            }
            is UiStatus.Failed -> {
                FailedCompo(state.status.message)
            }
            UiStatus.Success -> {
                SuccessCompo(state, onStockClick)
            }
            else -> {}
        }
    }
}

@Composable
fun Window(
    onAddStock: () -> Unit,
    onRefresh: () -> Unit,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    ToolbarWindow(
        title = stringResource(R.string.stock_list_page),
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

                IconButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = onAddStock
                ) {
                    Icon(
                        Icons.Default.Add,
                        stringResource(R.string.add_stock),
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
        },
        //secondBar = {},
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
    ErrorMessage(message = message)
}

@Composable
fun SuccessCompo(
    state: StockListState,
    onStockClick: (Stock) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        state.stockList.forEach { stock ->
            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .clickable { onStockClick(stock) }
            ) {
                Text(
                    text = stock.fullTicket(),
                    fontSize = 20.sp
                )
                Text(
                    text = stock.name,
                    fontSize = 12.sp
                )
            }
            //TODO: Add current price and change value from open
        }
    }
}


@Preview(group="Test")
@Composable
private fun StockListPage_Preview() {
    val stockList = listOf(
        Stock(0, "Valor 1", "TCK1", Market(0, "Market9", "MK9", "€")),
        Stock(0, "Valor 2", "TCK2", Market(0, "Market9", "MK9", "€")),
        Stock(0, "Valor A", "TCKA", Market(0, "MarketZ", "MKZ", "€"))
    )
    val state = StockListState(status = UiStatus.Success, stockList = stockList)
    StockListPage(state, {}, {}, {})
}