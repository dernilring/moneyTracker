package com.example.moneytracker

import java.lang.ProcessBuilder.Redirect.to

fun buildPieSectors(spendings: List<Spending>): List<Sector>{

    val grouped : Map<Category, List<Spending>> = spendings.groupBy { it.category }
    val mapped : Map<Category, Double> = grouped.mapValues { (_,list) -> list.sumOf { it.amount } }
    val filtered : Map<Category, Double> = mapped.filterValues{ it > 0.0}

    val total = filtered.values.sum()
    if (total <= 0.0 ) return emptyList()

    val sorted = filtered.entries.sortedByDescending { it.value }

    var currentAngle = -90f
    val sectors = mutableListOf<Sector>()

    for ((category, amount) in sorted) {
        val fraction = (amount / total).toFloat()
        val sweep = fraction * 360f
        sectors += Sector(
            category,
            amount,
            currentAngle,
            sweep,
            fraction
        )
        currentAngle += sweep
    }
    return sectors

}