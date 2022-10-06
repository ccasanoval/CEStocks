package com.cesoft.cestocks.domain.entities

data class StockHistory(
    val stock: Stock,
    val value: List<Quote>
)