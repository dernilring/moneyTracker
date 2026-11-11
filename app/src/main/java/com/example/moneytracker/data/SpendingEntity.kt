package com.example.moneytracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moneytracker.Category

@Entity(tableName = "spendings")
data class SpendingEntity(
    @PrimaryKey val id: String,
    val category: Category,
    val title : String,
    val amount: Double,
    val date: Long,
    val note: String?
)
