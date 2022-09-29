package com.cesoft.cestocks.ui.components.pages.stocklist

sealed class StockListSideEffect {
    data class ShowDetails(val id: Int): StockListSideEffect()
}
