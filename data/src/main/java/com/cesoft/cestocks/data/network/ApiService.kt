package com.cesoft.cestocks.data.network

import com.cesoft.cestocks.data.network.entities.HistoricalPriceEntity
import com.cesoft.cestocks.data.network.entities.StockEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Stock-Historical-Price
    //https://site.financialmodelingprep.com/developer/docs/#Stock-Historical-Price

    //https://financialmodelingprep.com/api/v3/historical-price-full/AAPL?serietype=line&apikey=1a98e7d5b8c2fa86eafa366b9ed90564
    @GET("historical-price-full/{symbol}")
    suspend fun getHistoricalPrice(
        @Path("symbol") symbol: String,
        @Query("apikey") apikey: String,
        @Query("serietype") serietype: String = "line"
    ): HistoricalPriceEntity

    //https://financialmodelingprep.com/api/v3/search?query=AA&limit=10&exchange=NASDAQ&apikey=YOUR_API_KEY
    //exchange = ETF | MUTUAL_FUND | COMMODITY | INDEX | CRYPTO | FOREX | TSX | AMEX | NASDAQ | NYSE | EURONEXT | XETRA | NSE | LSE
    //search
    //search-ticker
    //search-name
    @GET("search")
    suspend fun search(
        @Query("apikey") apikey: String,
        @Query("query") query: String,
        @Query("exchange") exchange: String,
        @Query("limit") limit: String = "10"
    ): List<StockEntity>

    //https://financialmodelingprep.com/api/v3/quote/AAPL?apikey=YOUR_API_KEY
    @GET("quote")
    suspend fun quote(
        @Path("symbol") symbol: String,//Or symbols
        @Query("apikey") apikey: String
    ): List<StockEntity>
}
