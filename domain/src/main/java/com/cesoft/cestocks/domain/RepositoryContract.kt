package com.cesoft.cestocks.domain

import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.entities.StockHistory

interface RepositoryContract {
    suspend fun search(value: String, market: String): List<Stock>
    suspend fun getUserStocks(): List<Stock>
    //suspend fun getUserStockById(id: Long)
    suspend fun addUserStock(stock: Stock): Long
    suspend fun getStockHistory(id: Long, period: String): StockHistory?
}