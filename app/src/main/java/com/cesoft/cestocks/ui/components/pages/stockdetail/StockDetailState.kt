package com.cesoft.cestocks.ui.components.pages.stockdetail

import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.entities.StockHistory
import com.cesoft.cestocks.ui.common.UiStatus

data class StockDetailState(
    val status: UiStatus? = null,
    val stockHistory: StockHistory? = null
)
