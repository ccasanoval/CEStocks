package com.cesoft.cestocks.ui.components.pages.stocklist

import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.ui.common.UiStatus

data class StockListState(
    val status: UiStatus? = null,
    val stockList: List<Stock> = listOf()
)
