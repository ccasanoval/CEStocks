package com.cesoft.cestocks

import com.cesoft.cestocks.ui.components.pages.init.InitViewModel
import com.cesoft.cestocks.ui.components.pages.stocklist.StockListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        InitViewModel(get())
    }
    viewModel {
        StockListViewModel(get())
    }
}
