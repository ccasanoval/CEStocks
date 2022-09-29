package com.cesoft.cestocks.ui.components.pages.stocklist

import com.cesoft.cestocks.domain.entities.Stock

sealed class StockListSideEffect {
    data class ShowDetails(val stock: Stock): StockListSideEffect()
}
