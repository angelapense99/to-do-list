package com.example.to_do_list.data

class TodoRepository {
    fun getTodos(): List<Todo>{
        return listOf(
            Todo(1, "einkaufen", false, 2),
            Todo(2, "aufräumen", true, 3),
            Todo(3, "Urlaub planen", false, 3)
        )
    }
}
