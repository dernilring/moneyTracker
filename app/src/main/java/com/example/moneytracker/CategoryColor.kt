package com.example.moneytracker

import androidx.compose.ui.graphics.Color
import com.example.moneytracker.ui.theme.Entertaiment
import com.example.moneytracker.ui.theme.Food
import com.example.moneytracker.ui.theme.Health
import com.example.moneytracker.ui.theme.Transport
import com.example.moneytracker.ui.theme.Home

val CategoryColor : Map<Category, Color > = mapOf(
    Category.FOOD to Food,
    Category.TRANSPORT to Transport,
    Category.HOME to Home,
    Category.ENTERTAINMENT to Entertaiment,
    Category.HEALTH to Health
)
