package com.cesoft.cestocks.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val MarketTableName = "markets"
@Entity(
    tableName = MarketTableName
)
data class MarketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val ticket: String,
    val currency: String
)