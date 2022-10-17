package com.cesoft.cestocks.data.repo

import com.cesoft.cestocks.data.BuildConfig
import com.cesoft.cestocks.data.database.AppDatabase
import com.cesoft.cestocks.data.database.entities.toDatabase
import com.cesoft.cestocks.data.database.entities.toModel
import com.cesoft.cestocks.data.network.NetworkDataSource
import com.cesoft.cestocks.data.network.entities.QuoteEntity
import com.cesoft.cestocks.data.network.entities.toModel
import com.cesoft.cestocks.domain.RepositoryContract
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.entities.StockHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: AppDatabase): RepositoryContract {

    //REMOTE ---------------------------------------------------------------------------------------

    suspend fun refreshMarkets() {
        withContext(Dispatchers.IO) {
        }
    }
    suspend fun refreshTickers() {
        withContext(Dispatchers.IO) {
        }
    }

    override suspend fun search(value: String, market: String): List<Stock> {
        //TODO: si no hay conexion, busca en los que ya estamos usando (local db)
        //exchange = ETF | MUTUAL_FUND | COMMODITY | INDEX | CRYPTO | FOREX | TSX | AMEX | NASDAQ | NYSE | EURONEXT | XETRA | NSE | LSE
        val stocks = NetworkDataSource.apiService.search(
            apikey = BuildConfig.API_KEY,
            query = value,
            exchange = market,
            limit = 50
        )
        return stocks.toModel()
    }

    //TODO: Devolver sealed class con Success / Fail ?
    //1min, 5min, 15min, 30min, 1hour, 4hour
    override suspend fun getStockHistory(id: Long, period: String): StockHistory? {
        val stock = getUserStockById(id)
        stock?.let {
            val value: List<QuoteEntity> = NetworkDataSource.apiService.getHistoricalPrice(
                symbol = it.ticker,
                period = period,
                apikey = BuildConfig.API_KEY
            )
            return StockHistory(stock, value.toModel())
        }
        return null
    }


    // LOCAL ---------------------------------------------------------------------------------------

    override suspend fun getUserStockById(id: Long): Stock? {
        return database.getStockDao().getById(id)?.toModel()
    }

    override suspend fun getUserStocks(): List<Stock> {
        return database.getStockDao().getAll().toModel()
    }

    override suspend fun addUserStock(stock: Stock): Long {
        val idMarket = database.getMarketDao().insert(stock.market.toDatabase())
        return database.getStockDao().insert(stock.toDatabase(idMarket))
    }
}