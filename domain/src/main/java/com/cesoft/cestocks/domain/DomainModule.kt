package com.cesoft.cestocks.domain

import com.cesoft.cestocks.domain.usecases.AddStockUseCase
import com.cesoft.cestocks.domain.usecases.GetStockHistoryUseCase
import com.cesoft.cestocks.domain.usecases.GetUserStockListUseCase
import com.cesoft.cestocks.domain.usecases.SearchUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetUserStockListUseCase(get())
    }
    factory {
        GetStockHistoryUseCase(get())
    }
    factory {
        SearchUseCase(get())
    }
    factory {
        AddStockUseCase(get())
    }
}
