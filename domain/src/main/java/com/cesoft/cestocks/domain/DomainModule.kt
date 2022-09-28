package com.cesoft.cestocks.domain

import com.cesoft.cestocks.domain.usecases.GetUserStockListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetUserStockListUseCase()
    }
}
