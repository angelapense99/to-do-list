package com.example.to_do_list.data

data class Todo(
    val id: Int,
    val title: String,
    val done: Boolean,
    val priority: Int
)
