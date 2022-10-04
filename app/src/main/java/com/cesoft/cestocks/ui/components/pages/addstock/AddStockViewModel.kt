package com.cesoft.cestocks.ui.components.pages.addstock

import androidx.lifecycle.ViewModel
import com.cesoft.cestocks.domain.usecases.SearchUseCase
import com.cesoft.cestocks.ui.common.UiStatus
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddStockViewModel(
    private val searchUseCase: SearchUseCase
) : ContainerHost<AddStockState, AddStockSideEffect>, ViewModel() {
    override val container = container<AddStockState, AddStockSideEffect>(AddStockState())

    fun onSearch(value: String, market: String) {
        intent {
            reduce { state.copy(status = UiStatus.Loading) }
            val data = searchUseCase(value, market)
            if(data.isNotEmpty()) {
                reduce { state.copy(status = UiStatus.Success, data = data) }
                postSideEffect(AddStockSideEffect.Completed)
            } else {
                reduce { state.copy(status = UiStatus.Failed()) }
            }
        }
    }
}