package com.cesoft.cestocks.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.cesoft.cestocks.data.database.entities.MarketEntity
import com.cesoft.cestocks.data.database.entities.MarketTableName

@Dao
interface MarketDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(market: MarketEntity): Long

    @Insert
    suspend fun insertAll(markets: List<MarketEntity>)

    @Update
    suspend fun update(stock: MarketEntity)

    @Delete
    suspend fun delete(stock: MarketEntity)

    @Query("SELECT * FROM $MarketTableName WHERE id=:id")
    suspend fun getById(id: Int): MarketEntity?

    @Query("SELECT * FROM $MarketTableName")
    suspend fun getAll(): List<MarketEntity>
}
