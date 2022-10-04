package com.cesoft.cestocks.ui.components.pages.addstock

import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.ui.common.UiStatus

data class AddStockState(
    val status: UiStatus? = null,
    val data: List<Stock>? = null
)
