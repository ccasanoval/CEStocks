package com.cesoft.cestocks.ui.components.pages.stocklist

import androidx.lifecycle.ViewModel
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.usecases.GetUserStockListUseCase
import com.cesoft.cestocks.ui.common.UiStatus
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class StockListViewModel(
    private val getUserStockListUseCase: GetUserStockListUseCase
) : ContainerHost<StockListState, StockListSideEffect>, ViewModel() {
    override val container = container<StockListState, StockListSideEffect>(
        StockListState()
    )

    init {
        fetchData()
    }

    fun retry() {
        if (container.stateFlow.value.status != UiStatus.Loading) {
            fetchData()
        }
    }

    fun onStockClick(stock: Stock) {
        intent {
            postSideEffect(StockListSideEffect.ShowDetails(stock))
        }
    }

    private fun fetchData() {
        intent {
            reduce { state.copy(status = UiStatus.Loading) }
            val data = getUserStockListUseCase()
            if (data.isNotEmpty()) {
                reduce { state.copy(status = UiStatus.Success, stockList = data) }
            } else {
                reduce { state.copy(status = UiStatus.Failed(), stockList = listOf()) }
            }
        }
    }
}