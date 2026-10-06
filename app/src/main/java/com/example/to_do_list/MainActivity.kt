package com.example.to_do_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.to_do_list.ui.screens.TodoScreen
import com.example.to_do_list.ui.theme.TodolistTheme
import com.example.to_do_list.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     Entry point of the application.

     Sets up the Compose UI and connects the TodoViewModel
     via Hilt dependency injection.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // ViewModel provided by Hilt
            val viewModel: TodoViewModel = hiltViewModel()

            TodolistTheme {

                // Observes current list of todos
                val todos = viewModel.todos.collectAsState(initial = emptyList()).value
                val openTodos = viewModel.openTodos.collectAsState(initial = emptyList()).value
                val closedTodos = viewModel.closedTodos.collectAsState(initial = emptyList()).value

                TodoScreen(
                    stillTodos = openTodos,
                    alreadyDones = closedTodos,
                    addTodo = viewModel::addTodo,
                    updateCompletionStatus = viewModel::updateOpenTodo)
            }
        }
    }
}