package com.cesoft.cestocks.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock

data class StockFullEntity(
    @Embedded
    val stock: StockEntity,
    @Relation(parentColumn = "marketId", entityColumn = "id", entity = MarketEntity::class)
    val market: MarketEntity
) {
    fun toModel(): Stock {
        val market = Market(id=market.id, name=market.name, ticker=market.ticker, currency=market.currency)
        return Stock(id=stock.id, name=stock.name, ticker=stock.ticker, market=market)
    }
}

fun List<StockFullEntity>.toModel(): List<Stock> = map { it.toModel() }