package com.example.moneytracker

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

object NavBarItems {

    var BarItems = listOf(
        BarItem(
            title = "Home",
            image = R.drawable.outline_home_24,
            route = "home"
        ),
        BarItem(
            title = "Account",
            image = R.drawable.outline_account_box_24,
            route = "account"
        ),
        BarItem(
            title = "Analitics",
            image = R.drawable.outline_analytics_24,
            route = "analitics"
        ),
        BarItem(
            title = "Categories",
            image = R.drawable.outline_category_24,
            route = "categories"
        ),
        BarItem(
            title = "History",
            image = R.drawable.outline_history_24,
            route = "history"
        )
    )
}