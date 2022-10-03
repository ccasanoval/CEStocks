package com.cesoft.cestocks.ui.components.pages.init

import androidx.lifecycle.ViewModel
import com.cesoft.cestocks.domain.usecases.GetUserStockListUseCase
import com.cesoft.cestocks.ui.common.UiStatus
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class InitViewModel(
    private val getUserStockListUseCase: GetUserStockListUseCase
) : ContainerHost<InitState, InitSideEffect>, ViewModel() {
    override val container = container<InitState, InitSideEffect>(InitState())

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
                postSideEffect(InitSideEffect.Completed)
            } else {
                reduce { state.copy(status = UiStatus.Failed()) }
            }
        }
    }
}