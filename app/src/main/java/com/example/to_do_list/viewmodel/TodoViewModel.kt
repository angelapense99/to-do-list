package com.example.to_do_list.viewmodel

import com.example.to_do_list.data.TodoRepository

class TodoViewModel(private val repo: TodoRepository) {
    val todos = repo.getTodos()
}
