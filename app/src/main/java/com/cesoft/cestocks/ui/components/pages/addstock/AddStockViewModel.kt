package com.cesoft.cestocks.ui.components.pages.addstock

import androidx.lifecycle.ViewModel
import com.cesoft.cestocks.domain.entities.Stock
import com.cesoft.cestocks.domain.usecases.AddStockUseCase
import com.cesoft.cestocks.domain.usecases.SearchUseCase
import com.cesoft.cestocks.ui.common.UiStatus
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddStockViewModel(
    private val searchUseCase: SearchUseCase,
    private val addStockUseCase: AddStockUseCase
) : ContainerHost<AddStockState, AddStockSideEffect>, ViewModel() {
    override val container = container<AddStockState, AddStockSideEffect>(AddStockState())

    fun onSearch(value: String, market: String) {
        intent {
            reduce { state.copy(status = UiStatus.Loading) }
            val data = searchUseCase(value, market)
            if(data.isNotEmpty()) {
                reduce { state.copy(status = UiStatus.Success, data = data) }
            } else {
                reduce { state.copy(status = UiStatus.Failed()) }
            }
        }
    }

    fun onAddStock(stock: Stock) {
        intent {
            reduce { state.copy(status = UiStatus.Loading) }
            if(addStockUseCase(stock)) {
                postSideEffect(AddStockSideEffect.Back)
            }
            else {
                reduce { state.copy(status = UiStatus.Failed()) }
            }
        }
    }
}