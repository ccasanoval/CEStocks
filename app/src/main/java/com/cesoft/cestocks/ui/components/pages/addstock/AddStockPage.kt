package com.cesoft.cestocks.ui.components.pages.addstock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesoft.cestocks.R
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.ui.common.UiStatus
import com.cesoft.cestocks.ui.common.fullTicket
import com.cesoft.cestocks.ui.components.dlg.DownloadRetryMessage
import com.cesoft.cestocks.ui.components.dlg.DownloadingMessage

@Composable
fun AddStockPage(
    state: AddStockState,
    onSearch: (String, String) -> Unit
) {
    Window(onSearch) {
        when(state.status) {
            UiStatus.Loading -> {
                DownloadingMessage(
                    modifier = Modifier.wrapContentSize()
                )
            }
            is UiStatus.Failed -> {
                DownloadRetryMessage(
                    onRetry = { },
                    modifier = Modifier.wrapContentSize()
                )
            }
            UiStatus.Success -> {
                SuccessCompo(state.data!!)
            }
            null -> Unit
        }
    }
}

//TODO: Abstract this......
@Composable
fun Window(
    onSearch: ((String, String) -> Unit),
    content: @Composable (padding: PaddingValues) -> Unit
) {
    val searchText = remember { mutableStateOf("") }
    val searchMarket = remember { mutableStateOf("") }
    Scaffold(
         topBar = {
            TopAppBar {
                val image: Painter = painterResource(id = R.drawable.ic_launcher_foreground)
                Icon(image, "")
                Text(
                    text = stringResource(id = R.string.add_stock_page),
                    fontSize = 24.sp
                )
                //Spacer(modifier = Modifier.weight(1f))
            }
        }
    ) { padding ->

        Column {
            Dropdown { searchMarket.value = it }
            Row {
                TextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    modifier = Modifier
                        .size(56.dp)
                        .background(MaterialTheme.colors.primary)
                        .padding(end = 8.dp),
                    onClick = { onSearch(searchText.value, searchMarket.value) }
                ) {
                    Icon(
                        Icons.Default.Search,
                        stringResource(R.string.search),
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }

            Row {
                content(padding)
            }
        }
    }
}


@Composable
fun Dropdown(onSelected: (String) -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val items = stringArrayResource(R.array.market_types)
    val selectedIndex = remember { mutableStateOf(0) }

    Row(modifier = Modifier.height(56.dp)) {
        Text(
            text = stringResource(R.string.market),
            fontSize = 22.sp,
            modifier = Modifier.padding(4.dp))
        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
            Text(
                text = items[selectedIndex.value],
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded.value = true }),
                fontSize = 22.sp
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        selectedIndex.value = index
                        expanded.value = false
                        onSelected(s)
                    }) {
                        Text(text = s)
                    }
                }
            }
        }
    }
}

@Composable
fun SuccessCompo(data: List<Stock>) {
    Column {
        data.forEach { stock ->
            Row {
                Text(text = stock.fullTicket())
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = stock.name)
            }
        }
    }
}

@Preview(group = "Test")
@Composable
private fun InitPage_Preview() {
    AddStockPage(AddStockState(UiStatus.Success)) { _,_ -> }
}