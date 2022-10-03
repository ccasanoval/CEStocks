package com.cesoft.cestocks.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cesoft.cestocks.data.database.entities.StockEntity
import com.cesoft.cestocks.data.database.entities.StockFullEntity
import com.cesoft.cestocks.data.database.entities.StockTableName

@Dao
interface StockDao {
    @Insert
    suspend fun insert(market: StockEntity)

    @Insert
    suspend fun insertAll(markets: List<StockEntity>)

    @Update
    suspend fun update(stock: StockEntity)

    @Delete
    suspend fun delete(stock: StockEntity)

    @Query("SELECT * FROM $StockTableName WHERE id=:id")
    suspend fun getById(id: Int): StockFullEntity?

    @Query("SELECT * FROM $StockTableName")
    suspend fun getAll(): List<StockFullEntity>
}
