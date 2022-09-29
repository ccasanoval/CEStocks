package com.cesoft.cestocks.domain.entities

import java.math.BigDecimal
import java.util.*

data class StockHistory(
    val stock: Stock,
    val dates: List<Date>,
    val prices: List<BigDecimal>
)