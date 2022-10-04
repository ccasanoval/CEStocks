package com.cesoft.cestocks.ui.components.pages.stocklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesoft.cestocks.R
import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.common.fullTicket
import com.cesoft.cestocks.ui.components.dlg.DownloadingMessage
import com.cesoft.cestocks.ui.components.dlg.ErrorMessage

@Composable
fun StockListPage(
    state: StockListState,
    onStockClick: (Stock) -> Unit,
    onAddStock: () -> Unit
) {
    Window(onAddStock = onAddStock) {
        when (state.status) {
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
    onAddStock: (() -> Unit),
    content: @Composable (padding: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth()) {
                val image: Painter = painterResource(id = R.drawable.ic_launcher_foreground)
                Icon(image, "")

                Text(
                    text = stringResource(id = R.string.stock_list_page),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    modifier = Modifier.then(Modifier.size(32.dp)),
                    onClick = onAddStock
                ) {
                    Icon(
                        Icons.Default.Add,
                        stringResource(R.string.add_stock),
                        tint = Color.White
                    )
                }
            }
        }
    ) { padding ->
        content(padding)
    }
}

@Composable
fun LoadingCompo() {
    DownloadingMessage()
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
    Scaffold { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {
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
                    //TODO: Add current price and change from open
                }
            }
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
    StockListPage(state, {}, {})
}