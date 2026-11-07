package com.example.moneytracker

sealed class NavRoutes(val route: String) {
 object Home: NavRoutes("home")
 object Account: NavRoutes("account")
 object Analitics: NavRoutes("analitics")
 object Categories: NavRoutes("categories")
 object SpendingHistory: NavRoutes("history")
 object AddSpending: NavRoutes("addSpending")

}