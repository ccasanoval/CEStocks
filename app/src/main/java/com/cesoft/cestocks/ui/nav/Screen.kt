package com.cesoft.cestocks.ui.nav

import androidx.navigation.NavBackStackEntry

sealed class Screen(val route: String) {
    object Init: Screen(route = "init")
    object StockList: Screen(route = "stockList")
    object AddStock: Screen(route = "addStock")
    object StockDetail: Screen(route = "stockDetail/{id}") {
        fun createRoute(id: Long) = "stockDetail/$id"
        fun getArgumentId(entry: NavBackStackEntry): Long {
            return entry.arguments?.getString("id")?.toLong() ?: 0
        }
    }
}
