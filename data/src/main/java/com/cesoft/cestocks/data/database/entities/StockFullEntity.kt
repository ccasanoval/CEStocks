package com.cesoft.cestocks.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class StockFullEntity(
    @Embedded
    val stock: StockEntity,

    @Relation(parentColumn = "marketId", entityColumn = "id", entity = MarketEntity::class)
    val market: MarketEntity
)
