package com.example.to_do_list.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<Todo>>

    @Insert
    suspend fun insert(task: Todo)

    @Delete
    suspend fun delete(task: Todo)
}
