package com.cesoft.cestocks.data.network.entities

import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock

//"symbol" : "PRAA",
//"name" : "PRA Group, Inc.",
//"currency" : "USD",
//"stockExchange" : "NasdaqGS",
//"exchangeShortName" : "NASDAQ"
data class StockEntity(
    val symbol: String,
    val name: String,
    val currency: String?,
    val stockExchange: String,
    val exchangeShortName: String
) {
    fun toModel() = Stock(
        id=0,
        name=name,
        ticker=symbol,
        market=Market(id=0, name=stockExchange, ticker=exchangeShortName, currency=currency ?: ""))
}

fun List<StockEntity>.toModel() = this.map { it.toModel() }
