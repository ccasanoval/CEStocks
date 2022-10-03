package com.cesoft.cestocks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesoft.cestocks.data.database.dao.MarketDao
import com.cesoft.cestocks.data.database.dao.StockDao
import com.cesoft.cestocks.data.database.entities.MarketEntity
import com.cesoft.cestocks.data.database.entities.StockEntity

@Database(
    entities = [
        MarketEntity::class,
        StockEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMarketDao(): MarketDao
    abstract fun getStockDao(): StockDao
}
