package com.cesoft.cestocks.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    val id: Int,
    val name: String,
    val ticker: String,
    val marketId: Int
)