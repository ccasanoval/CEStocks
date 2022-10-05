package com.cesoft.cestocks.domain.usecases

import com.cesoft.cestocks.domain.RepositoryContract
import com.cesoft.cestocks.domain.entities.Stock

class AddStockUseCase(
    private val repository: RepositoryContract
) {
    suspend operator fun invoke(stock: Stock): Boolean {
        return repository.addUserStock(stock) >= 0
    }
}
