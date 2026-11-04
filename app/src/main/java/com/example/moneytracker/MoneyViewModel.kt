package com.example.moneytracker

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


class MoneyViewModel : ViewModel() {

    val spendings = mutableStateListOf<Spending>(
        Spending(category = Category.FOOD, title = "Сыр", amount = 4.08, date = "02-11-2026", note = null),
        Spending(category = Category.FOOD, title = "Хлеб", amount = 1.25, date = "02-11-2026", note = "бородинский"),
        Spending(category = Category.FOOD, title = "Кофе", amount = 3.50, date = "02-11-2026", note = "капучино"),
        Spending(category = Category.TRANSPORT, title = "Метро", amount = 0.90, date = "02-11-2026", note = null),
        Spending(category = Category.TRANSPORT, title = "Такси", amount = 12.40, date = "02-11-2026", note = "домой поздно"),
        Spending(category = Category.ENTERTAINMENT, title = "Кино", amount = 9.00, date = "02-11-2026", note = "билет на вечер"),
        Spending(category = Category.FOOD, title = "Обед", amount = 7.80, date = "02-11-2026", note = "бизнес-ланч"),
        Spending(category = Category.HEALTH, title = "Аптека", amount = 5.60, date = "02-11-2026", note = "витамины"),
        Spending(category = Category.FOOD, title = "Йогурт", amount = 1.10, date = "02-11-2026", note = null),
        Spending(category = Category.HOME, title = "Мыло", amount = 2.30, date = "02-11-2026", note = null),

        // === 01-11-2026 ===
        Spending(category = Category.FOOD, title = "Сыр", amount = 4.08, date = "01-11-2026", note = null),
        Spending(category = Category.FOOD, title = "Яблоки", amount = 2.70, date = "01-11-2026", note = "1 кг"),
        Spending(category = Category.TRANSPORT, title = "Автобус", amount = 0.75, date = "01-11-2026", note = null),
        Spending(category = Category.ENTERTAINMENT, title = "Подписка", amount = 5.99, date = "01-11-2026", note = "музыка"),
        Spending(category = Category.FOOD, title = "Ужин", amount = 11.20, date = "01-11-2026", note = null),

        // === 31-10-2026 ===
        Spending(category = Category.FOOD, title = "Молоко", amount = 1.40, date = "31-10-2026", note = null),
        Spending(category = Category.HOME, title = "Лампочка", amount = 3.20, date = "31-10-2026", note = null),
        Spending(category = Category.TRANSPORT, title = "Метро", amount = 0.90, date = "31-10-2026", note = null),
        Spending(category = Category.HEALTH, title = "Спортзал", amount = 20.00, date = "31-10-2026", note = "месячный абонемент"),
        Spending(category = Category.FOOD, title = "Пицца", amount = 14.50, date = "31-10-2026", note = "пятница"),

        // === 30-10-2026 ===
        Spending(category = Category.FOOD, title = "Сыр", amount = 4.08, date = "30-10-2026", note = null),
        Spending(category = Category.FOOD, title = "Батон", amount = 0.95, date = "30-10-2026", note = null),
        Spending(category = Category.TRANSPORT, title = "Такси", amount = 8.30, date = "30-10-2026", note = "на встречу"),
        Spending(category = Category.ENTERTAINMENT, title = "Книга", amount = 12.00, date = "30-10-2026", note = null),
        Spending(category = Category.HOME, title = "Порошок", amount = 6.40, date = "30-10-2026", note = null),

        // === 29-10-2026 ===
        Spending(category = Category.FOOD, title = "Обед", amount = 6.90, date = "29-10-2026", note = "кафе у офиса"),
        Spending(category = Category.FOOD, title = "Кофе", amount = 3.50, date = "29-10-2026", note = null),
        Spending(category = Category.HEALTH, title = "Таблетки", amount = 4.10, date = "29-10-2026", note = null),
        Spending(category = Category.TRANSPORT, title = "Метро", amount = 0.90, date = "29-10-2026", note = null),

        // === 28-10-2026 ===
        Spending(category = Category.FOOD, title = "Сыр", amount = 4.08, date = "28-10-2026", note = null),
        Spending(category = Category.FOOD, title = "Курица", amount = 7.20, date = "28-10-2026", note = "филе"),
        Spending(category = Category.ENTERTAINMENT, title = "Игра", amount = 15.00, date = "28-10-2026", note = "скидка"),
        Spending(category = Category.HOME, title = "Салфетки", amount = 1.80, date = "28-10-2026", note = null),
        Spending(category = Category.TRANSPORT, title = "Автобус", amount = 0.75, date = "28-10-2026", note = null)
    )

    fun addToList() {
        spendings.add(
            Spending(
                category = Category.TRANSPORT,
                title = "Автобус",
                amount = 0.75,
                date = "28-10-2026",
                note = null
            )
        )
    }

    fun removeFromList(spending: Spending) {
        spendings.removeAll { it.id == spending.id }
    }

    fun changeItem(spending: Spending, newSpending: Spending) {
        val index = spendings.indexOfFirst { it.id == spending.id }
        if (index != -1) {
            spendings[index] = newSpending.copy(id = spending.id)
        }
    }
    fun filterSpendings(category: String){
        spendings.filter { spending -> spending.category.toString() == category }
    }
}