package com.example.to_do_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.to_do_list.data.Todo
import com.example.to_do_list.ui.screens.TodoScreen
import com.example.to_do_list.ui.screens.TodoScreenCallbacks
import com.example.to_do_list.ui.screens.TodoScreenState
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

                val states = TodoScreenState(
                    stillTodos = viewModel.openTodos.collectAsState(initial = emptyList()).value,
                    alreadyDones = viewModel.closedTodos.collectAsState(initial = emptyList()).value,
                    text = viewModel.text.collectAsState().value,
                    priority = viewModel.priority.collectAsState().value
                )

                val callbacks = object: TodoScreenCallbacks {
                    override fun addTodo(
                        title: String,
                        priority: Int,
                    ) = viewModel.addTodo(title, priority)

                    override fun updateCompletionStatus(
                        todo: Todo,
                    ) = viewModel.updateCompletionStatus(todo)

                    override fun updateTodo(
                        todo: Todo,
                    ) = viewModel.updateTodo(todo)


                    override fun deleteTodo(
                        todo: Todo
                    ) = viewModel.deleteTodo(todo)

                    override fun onTextChanged(
                        title: String
                    ) = viewModel.onTextChanged(title)

                    override fun onPriorityChanged(
                        newPriority: Int
                    ) = viewModel.onPriorityChanged(newPriority)
                }


                TodoScreen(
                    states,
                    callbacks,
                )
            }
        }
    }
}