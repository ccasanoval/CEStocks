package com.cesoft.cestocks.domain.entities

//import java.util.Currency

data class Market(
    val id: Long,
    val name: String,
    val ticker: String,
    val currency: String
)