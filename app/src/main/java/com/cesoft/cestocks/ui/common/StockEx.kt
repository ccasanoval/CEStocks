package com.cesoft.cestocks.ui.common

import com.cesoft.cestocks.domain.entities.Stock

fun Stock.fullTicket() = "$ticker.${market.ticket}"