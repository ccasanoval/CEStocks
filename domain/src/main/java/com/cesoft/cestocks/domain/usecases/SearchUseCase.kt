package com.cesoft.cestocks.domain.usecases

import com.cesoft.cestocks.domain.RepositoryContract
import com.cesoft.cestocks.domain.entities.Stock

class SearchUseCase(
    private val repository: RepositoryContract
) {
    suspend operator fun invoke(value: String, market: String): List<Stock> {
        return repository.search(value, market)
    }
}