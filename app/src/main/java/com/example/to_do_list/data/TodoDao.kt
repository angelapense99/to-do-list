package com.example.to_do_list.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Todo entities.
 *
 * Defines all database operations related to Todo items.
 * Room generates the implementation automatically at compile time.
 */

@Dao
interface TodoDao {

    /**
     * Returns a continuous stream of all Todo items in the database.
     *
     * The Flow automatically emits a new list whenever the table changes.
     *
     * @return Flow emitting the current list of Todo items
     */
    @Query("SELECT * FROM tasks ORDER BY priority ASC")
    fun getAll(): Flow<List<Todo>>

    //Filters between active and completed todos.
    @Query("Select * FROM tasks WHERE done = 0 ORDER BY priority ASC")
    fun getAllOpen(): Flow<List<Todo>>

    @Query("Select * FROM tasks WHERE done = 1")
    fun getAllClosed(): Flow<List<Todo>>

    /**
     * Inserts a new Todo item into the database.
     *
     * @param task The Todo item to insert
     */
    @Insert
    suspend fun insert(task: Todo)

    /**
     * Deletes an existing Todo item from the database.
     *
     * @param task The Todo item to remove
     */
    @Delete
    suspend fun delete(task: Todo)

    @Update
    suspend fun update(task: Todo)
}
