package com.cesoft.cestocks.ui.components.pages.stockdetail

import androidx.lifecycle.ViewModel
import com.cesoft.cestocks.domain.usecases.GetStockHistoryUseCase
import com.cesoft.cestocks.ui.common.UiStatus
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class StockDetailViewModel(
    private val id: Long,
    //private val getStockUseCase: GetStockUseCase,
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
            //val stock = getStockUseCase(id=id)
            val data = getStockHistoryUseCase(id=id, period="1min")
            if (data != null) {
                reduce { state.copy(status = UiStatus.Success, stockHistory = data) }
            } else {
                reduce { state.copy(status = UiStatus.Failed(), stockHistory = null) }
            }
        }
    }
}