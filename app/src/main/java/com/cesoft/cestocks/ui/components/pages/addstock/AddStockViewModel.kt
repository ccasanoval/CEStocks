package com.cesoft.cestocks.ui.components.pages.addstock

import androidx.lifecycle.ViewModel
import com.cesoft.cestocks.domain.usecases.GetUserStockListUseCase
import com.cesoft.cestocks.ui.common.UiStatus
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddStockViewModel(
    private val getUserStockListUseCase: GetUserStockListUseCase
) : ContainerHost<AddStockState, AddStockSideEffect>, ViewModel() {
    override val container = container<AddStockState, AddStockSideEffect>(AddStockState())

    init {
        fetchData()
    }

    fun retry() {
        if(container.stateFlow.value.status != UiStatus.Loading) {
            fetchData()
        }
    }

    private fun fetchData() {
        intent {
            reduce { state.copy(status = UiStatus.Loading) }
            //TODO: Other config loading?
            if(getUserStockListUseCase().isNotEmpty()) {
                reduce { state.copy(status = UiStatus.Success) }
                postSideEffect(AddStockSideEffect.Completed)
            } else {
                reduce { state.copy(status = UiStatus.Failed()) }
            }
        }
    }
}