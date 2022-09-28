package com.cesoft.cestocks.ui.nav

import androidx.navigation.NavBackStackEntry

sealed class Screen(val route: String) {
    object Init: Screen(route = "init")
    object StockList: Screen(route = "stockList")
    object StockDetail: Screen(route = "stockDetail/{id}") {
        fun createRoute(id: Int) = "stockDetail/$id"
        fun getArgumentId(entry: NavBackStackEntry): Int {
            return entry.arguments?.getString("id")?.toInt() ?: 0
        }
    }
}
