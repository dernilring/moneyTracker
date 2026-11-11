package com.example.moneytracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moneytracker.Spending
import kotlinx.coroutines.flow.Flow

@Dao
interface SpendingDao {
    @Query("SELECT * FROM spendings ORDER BY date DESC")
    fun getAllSpendings(): Flow<List<SpendingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpending(spending: SpendingEntity)
    @Update
    suspend fun updateSpending(spending: SpendingEntity)

    @Query("DELETE FROM spendings WHERE id = :id")
    suspend fun deleteSpendingById(id: String)

    @Delete
    suspend fun deleteSpending(spending: SpendingEntity)


}