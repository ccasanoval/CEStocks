package com.cesoft.cestocks.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cesoft.cestocks.domain.entities.Market

const val MarketTableName = "markets"
@Entity(
    tableName = MarketTableName
)
data class MarketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val ticker: String,
    val currency: String
)

fun Market.toDatabase(): MarketEntity =
    MarketEntity(id=id, name=name, ticker=ticker, currency=currency)
