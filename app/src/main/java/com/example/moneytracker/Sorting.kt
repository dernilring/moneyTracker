package com.example.moneytracker

enum class Sorting(val displayName: String) {
    ASCENDING("сначала дешевые"),
    DECREASING("сначала дорогие");


}
fun List<Spending>.sortedBySum(order: Sorting): List<Spending> = when (order) {
    Sorting.ASCENDING -> sortedBy { it.amount }
    Sorting.DECREASING -> sortedByDescending { it.amount }
}