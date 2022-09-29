package com.cesoft.cestocks.domain

import com.cesoft.cestocks.domain.usecases.GetStockHistoryUseCase
import com.cesoft.cestocks.domain.usecases.GetUserStockListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetUserStockListUseCase()
    }
    factory {
        GetStockHistoryUseCase()
    }
}
