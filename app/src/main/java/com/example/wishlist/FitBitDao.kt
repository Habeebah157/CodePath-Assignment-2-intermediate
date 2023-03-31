package com.example.wishlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FitBitDao{
    @Query("SELECT * FROM nutrition_table")
    fun getAll(): Flow<List<Fitbitentity>>

    @Insert
    fun insert(nutrition: Fitbitentity)

    @Query("DELETE FROM nutrition_table")
    fun deleteAll()
}
