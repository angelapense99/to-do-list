package com.example.to_do_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_list.data.Todo
import com.example.to_do_list.data.TodoDao
import com.example.to_do_list.data.TodoRepository
import kotlinx.coroutines.launch
import kotlin.text.insert

class TodoViewModel(private val dao: TodoDao): ViewModel() {
    val todos = dao.getAll()
    fun addTodo(title: String) {
        viewModelScope.launch {
            dao.insert(Todo(title=title, done = false, priority = 2))
        }
    }
}