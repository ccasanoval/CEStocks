package com.cesoft.cestocks.domain.entities

import java.math.BigDecimal
import java.math.BigInteger
import java.util.Date

data class Quote(
    val date: Date,
    val open: BigDecimal,
    val low: BigDecimal,
    val high: BigDecimal,
    val close: BigDecimal,
    val volume: BigInteger
)
