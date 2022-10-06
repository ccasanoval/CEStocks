package com.cesoft.cestocks.data.network.entities

import android.annotation.SuppressLint
import com.cesoft.cestocks.domain.entities.Quote
import java.math.BigDecimal
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*

/*
"date" : "2020-03-02 15:59:00",
"open" : 297.230000000000,
"low" : 297.230000000000,
"high" : 298.280000000000,
"close" : 298.252300000000,
"volume" : 78679246*/
data class QuoteEntity(
    val date: String,
    val open: BigDecimal,
    val low: BigDecimal,
    val high: BigDecimal,
    val close: BigDecimal,
    val volume: BigInteger
) {
    companion object {
        @SuppressLint("SimpleDateFormat")
        private val sdf = SimpleDateFormat("yyyy-MM-dd")
    }

    fun toModel() = Quote(
        date = sdf.parse(date),
        open = open,
        low = low,
        high = high,
        close = close,
        volume = volume
    )
}

fun List<QuoteEntity>.toModel() = this.map { it.toModel() }

