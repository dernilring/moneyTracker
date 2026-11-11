package com.example.moneytracker.data

import com.example.moneytracker.Spending

fun SpendingEntity.toModel(): Spending = Spending(
    id = id,
    title = title,
    category = category,
    amount = amount,
    date = date,
    note = note
)
fun Spending.toEntity(): SpendingEntity = SpendingEntity(
    id = id,
    title = title,
    category = category,
    amount = amount,
    date = date,
    note = note
)