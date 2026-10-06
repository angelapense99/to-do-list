package com.example.to_do_list.viewmodel

import com.example.to_do_list.data.Todo
import com.example.to_do_list.data.TodoDao
import jakarta.inject.Inject


/**
 * Repository layer for Todo data operations.
 *
 * Acts as a single access point for data handling between
 * ViewModel and the Room database (TodoDao).
 *
 * This abstraction allows replacing or extending the data source
 * (e.g., adding network or cache layers) without changing the ViewModel.
 */
class UserRepository @Inject constructor(
    val dao: TodoDao
) {

    /**
     * Inserts a new Todo item into the database.
     *
     * @param todo The Todo item to insert
     */
    suspend fun insert(todo: Todo) = dao.insert(todo)

    /**
     * Deletes a Todo item from the database.
     *
     * @param todo The Todo item to delete
     */
    suspend fun delete(todo: Todo) = dao.delete(todo)

    /**
     * Updates the completion status of a Todo item from the database.
     *
     * @param openTodo The Todo item to update
     */

    suspend fun update(openTodo: Todo) = dao.update( openTodo  )
}