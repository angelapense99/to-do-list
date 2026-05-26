package com.example.to_do_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_list.data.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the To-do screen.
 *
 * Responsible for:
 * - exposing the list of to-dos from the repository
 * - handling user input for the text field
 * - adding and deleting to-do items
 *
 * Uses Hilt for dependency injection and viewModelScope
 * for executing database operations asynchronously.
 */

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    /**
     * Stream of all to-dos from the database.
     */
    val todos = repository.dao.getAll()

    /**
     * Holds the current text input value for the UI.
     */
    private val _text = MutableStateFlow("")

    /**
     * Public read-only access to the text input state.
     */
    val text: StateFlow<String> = _text.asStateFlow()


    /**
     * Updates the current text input value.
     *
     * @param newText New value entered by the user
     */
    fun onTextChanged(newText: String) {
        _text.value = newText
    }


    /**
     * Inserts a new to-do item into the database.
     *
     * @param title Title of the new to-do item
     */
    fun addTodo(title: String) {
        viewModelScope.launch {
            repository.insert(Todo(title=title, done = false, priority = 2))
        }
    }

    /**
     * Deletes an existing to-do item from the database.
     *
     * @param todo
     * The to-do item to remove
     */
    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }
}