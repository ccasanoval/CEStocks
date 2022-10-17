package com.cesoft.cestocks.domain.usecases

import com.cesoft.cestocks.domain.RepositoryContract
import com.cesoft.cestocks.domain.entities.StockHistory

class GetStockHistoryUseCase(
    private val repository: RepositoryContract
) {
    suspend operator fun invoke(id: Long, period: String): Pair<StockHistory?, String?> {
        //val stock = repository.getUserStockById(id)
        return repository.getStockHistory(id, period)
    }
}
