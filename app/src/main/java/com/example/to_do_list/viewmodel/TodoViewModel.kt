package com.example.to_do_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_list.data.Todo
import com.example.to_do_list.data.TodoDao
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.text.insert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    val todos = repository.dao.getAll()
    private val _text = MutableStateFlow("test")
    val text: StateFlow<String> = _text.asStateFlow()

    fun onTextChanged(newText: String) {
        _text.value = newText
    }
    fun addTodo(title: String) {
        viewModelScope.launch {
            repository.insert(Todo(title=title, done = false, priority = 2))
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }
}