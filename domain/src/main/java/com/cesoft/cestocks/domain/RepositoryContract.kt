package com.cesoft.cestocks.domain

import com.cesoft.cestocks.domain.entities.Stock

interface RepositoryContract {
    suspend fun search(value: String, market: String): List<Stock>
    suspend fun getUserStocks(): List<Stock>
    suspend fun addUserStock(stock: Stock): Long

}