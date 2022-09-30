package com.cesoft.cestocks.ui.components.pages.stockdetail

import androidx.lifecycle.ViewModel
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.usecases.GetStockHistoryUseCase
import com.cesoft.cestocks.domain.usecases.GetUserStockListUseCase
import com.cesoft.cestocks.ui.common.UiStatus
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class StockDetailViewModel(
    private val id: Int,
    private val getStockHistoryUseCase: GetStockHistoryUseCase
) : ContainerHost<StockDetailState, StockDetailSideEffect>, ViewModel() {
    override val container = container<StockDetailState, StockDetailSideEffect>(
        StockDetailState()
    )

    init {
        fetchData()
    }

    fun retry() {
        if (container.stateFlow.value.status != UiStatus.Loading) {
            fetchData()
        }
    }

    private fun fetchData() {
        intent {
            reduce { state.copy(status = UiStatus.Loading) }
            val data = getStockHistoryUseCase(id=id)
            if (data != null) {
                reduce { state.copy(status = UiStatus.Success, stockHistory = data) }
                //postSideEffect(InitSideEffect.Completed)
            } else {
                reduce { state.copy(status = UiStatus.Failed(), stockHistory = null) }
            }
        }
    }
}