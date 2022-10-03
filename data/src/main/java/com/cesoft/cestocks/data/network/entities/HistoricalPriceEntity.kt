package com.cesoft.cestocks.data.network.entities

import java.math.BigDecimal
import java.math.BigInteger

/*{
  "symbol" : "AAPL",
  "historical" : [ {
    "date" : "2022-08-11",
    "close" : 168.49
  }, {
    "date" : "2022-08-10",
    "close" : 169.24
  }, {
    "date" : "2022-08-09",
    "close" : 164.92
  }, {*//*data class HistoricalPrices(
    val symbol: String,
    val historical: List<HistoricalValue>
)

data class HistoricalValue(
    val date: String,
    val close: Float
)*/

//----

data class HistoricalPriceEntity(
    val date: String,
    val open: BigDecimal,
    val low: BigDecimal,
    val high: BigDecimal,
    val close: BigDecimal,
    val volume: BigInteger
)
/*
"date" : "2020-03-02 15:59:00",
"open" : 297.230000000000,
"low" : 297.230000000000,
"high" : 298.280000000000,
"close" : 298.252300000000,
"volume" : 78679246*/
