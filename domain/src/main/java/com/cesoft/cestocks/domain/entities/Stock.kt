package com.cesoft.cestocks.domain.entities

data class Stock(
    val id: Int,
    val name: String,
    val ticker: String,
    val market: Market
)