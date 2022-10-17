package com.cesoft.cestocks.domain.usecases

import com.cesoft.cestocks.domain.RepositoryContract
import com.cesoft.cestocks.domain.entities.Market
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.entities.StockHistory
import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.util.*

class GetStockUseCase(
    private val repository: RepositoryContract
) {
    suspend operator fun invoke(id: Long): Stock? {
        return repository.getUserStockById(id)
    }
}
