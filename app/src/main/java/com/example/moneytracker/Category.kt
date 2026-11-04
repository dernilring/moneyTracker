package com.example.moneytracker

import androidx.annotation.DrawableRes

enum class Category(val displayedName: String, @DrawableRes val icon: Int) {
    FOOD("Еда", R.drawable.outline_fastfood_24),
    TRANSPORT("Транспорт", R.drawable.outline_emoji_transportation_24),
    ENTERTAINMENT("Развлечения", R.drawable.outline_comedy_mask_24),
    HEALTH("Здоровье", R.drawable.outline_health_metrics_24),
    HOME("Дом", R.drawable.outline_home_24);

    companion object {
        fun fromName(name: String): Category? =
            entries.firstOrNull { it.name.equals(name, ignoreCase = true) }
    }
}