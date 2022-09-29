package com.cesoft.cestocks.domain.usecases

import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock

class GetUserStockListUseCase(
    //private val stockRepository: StockRepository,
    //private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(): List<Stock> {
        val mc = Market(0, "MC", "BME", "€")
        return listOf(
            Stock(0, "Santander", "SAN", mc),
            Stock(1, "Sacyr", "SCYR", mc),
            Stock(2, "OHLA", "OHLA", mc)
        )
    }
}
