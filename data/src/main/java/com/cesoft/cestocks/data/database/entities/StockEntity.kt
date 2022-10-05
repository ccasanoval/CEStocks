package com.cesoft.cestocks.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.cesoft.cestocks.domain.entities.Stock

const val StockTableName = "stocks"
@Entity(
    tableName = StockTableName,
    foreignKeys = [
        ForeignKey(
            entity = MarketEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("marketId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class StockEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val ticker: String,
    val marketId: Long
)

fun Stock.toDatabase(marketId: Long): StockEntity =
    StockEntity(id=id, name=name, ticker=ticker, marketId=marketId)