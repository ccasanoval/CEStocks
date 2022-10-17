package com.cesoft.cestocks.ui.common

import com.cesoft.cestocks.domain.entities.Stock

fun Stock.fullTicket() = "$ticker.${market.ticker}"
fun Stock.fullName() = "$name ($ticker.${market.ticker})"