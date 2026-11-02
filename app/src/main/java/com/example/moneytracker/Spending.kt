package com.example.moneytracker

import java.util.UUID

data class Spending(
    val id: String = UUID.randomUUID().toString(),
    val category: String,
    val title: String,
    val amount: Double,
    val date: String,
    val note: String?
)

