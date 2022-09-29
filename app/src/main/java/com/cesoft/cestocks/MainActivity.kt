package com.cesoft.cestocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cesoft.cestocks.ui.components.pages.init.InitPage
import com.cesoft.cestocks.ui.components.pages.init.InitSideEffect
import com.cesoft.cestocks.ui.components.pages.init.InitViewModel
import com.cesoft.cestocks.ui.components.pages.stockdetail.StockDetailPage
import com.cesoft.cestocks.ui.components.pages.stockdetail.StockDetailSideEffect
import com.cesoft.cestocks.ui.components.pages.stockdetail.StockDetailViewModel
import com.cesoft.cestocks.ui.components.pages.stocklist.StockListPage
import com.cesoft.cestocks.ui.components.pages.stocklist.StockListSideEffect
import com.cesoft.cestocks.ui.components.pages.stocklist.StockListViewModel
import com.cesoft.cestocks.ui.nav.Screen
import com.cesoft.cestocks.ui.theme.CEStocksTheme
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CEStocksTheme {
                window.statusBarColor = MaterialTheme.colorScheme.primary.toArgb()
                Box(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.Init.route) {
                        addInit(navController = navController)
                        addStockList(navController = navController)
                        addStockDetail(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun getComposeViewModelOwner(): ViewModelOwner {
    return ViewModelOwner.from(
        LocalViewModelStoreOwner.current!!,
        LocalSavedStateRegistryOwner.current
    )
}

@Composable
inline fun <reified T : ViewModel> getComposeViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
): T {
    val viewModelOwner = getComposeViewModelOwner()
    return KoinJavaComponent.getKoin().getViewModel(qualifier, { viewModelOwner }, parameters)
}

private fun NavGraphBuilder.addInit(navController: NavController) {
    composable(route = Screen.Init.route) {
        val viewModel = getComposeViewModel<InitViewModel>()
        val state by viewModel.container.stateFlow.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect {
                when(it) {
                    is InitSideEffect.Completed -> {
                        navController.navigate(route = Screen.StockList.route)
                    }
                }
            }
        }

        InitPage(
            state = state,
            onRetry = { viewModel.retry() }
        )
    }
}

private fun NavGraphBuilder.addStockList(navController: NavController) {
    composable(route = Screen.StockList.route) {
        val viewModel = getComposeViewModel<StockListViewModel>()
        val state by viewModel.container.stateFlow.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect { effect ->
                when(effect) {
                    is StockListSideEffect.ShowDetails -> {
                        navController.navigate(route = Screen.StockDetail.createRoute(effect.id))
                    }
                }
            }
        }

        StockListPage(
            state = state
        )
    }
}

private fun NavGraphBuilder.addStockDetail(navController: NavController) {
    composable(route = Screen.StockDetail.route) {
        val viewModel = getComposeViewModel<StockDetailViewModel>(
            parameters = { parametersOf(Screen.StockDetail.getArgumentId(it)) }
        )
        val state by viewModel.container.stateFlow.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect {
                when (it) {
                    is StockDetailSideEffect.Back -> {
                        navController.popBackStack()
                    }
                }
            }
        }
        StockDetailPage(
            state = state,
            onBack = { navController.popBackStack() }
        )
    }
}
