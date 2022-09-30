package com.cesoft.cestocks.domain.usecases

import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.entities.StockHistory
import java.math.BigDecimal
import java.util.*

class GetStockHistoryUseCase(
    //private val pokemonRepository: StockRepository,
    //private val configRepository: ConfigRepository
) {
    suspend operator fun invoke(id: Int): StockHistory? {
        val now = Date()
        val aDay = 24*60*60*1000

        val dates = mutableListOf<Date>()
        val prices = mutableListOf<BigDecimal>()
        for(i in 0..100) {
            dates.add(Date(now.time + aDay*i))
            prices.add(Math.random().toBigDecimal())
        }

        val market = Market(id=0,name="Market", ticket="Ticker", currency="€")
        val stock = Stock(id=id, name="Name", ticker="Ticker", market=market)
        return StockHistory(
            stock = stock,
            dates = dates,
            prices = prices
        )
    }
}
