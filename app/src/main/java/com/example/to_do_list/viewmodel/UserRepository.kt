package com.example.to_do_list.viewmodel

import com.example.to_do_list.data.Todo
import com.example.to_do_list.data.TodoDao
import jakarta.inject.Inject



class UserRepository @Inject constructor(
    val dao: TodoDao
) {
    suspend fun insert(todo: Todo) = dao.insert(todo )
    suspend fun delete(todo: Todo) = dao.delete(todo)
}
