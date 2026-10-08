package com.example.to_do_list.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.to_do_list.data.Todo
import com.example.to_do_list.ui.component.TodoItem
import com.example.to_do_list.viewmodel.TodoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect

/**
 * Main screen of the to-do application.
 *
 * Displays:
 * - a list of all to-dos
 * - an input field for creating new to-dos
 * - buttons for adding items
 *
 * The text input state is managed by the ViewModel.
 *
 * @param todos Current list of all to-dos
 * @param addTodo Callback used to add a new to-do
 * @param delete Callback used to delete a to-do
 * @param viewModel ViewModel responsible for managing UI state
 */

@Composable
fun TodoScreen(
    stillTodos: List<Todo>,
    alreadyDones: List<Todo>,
    addTodo: (String, Int) -> Unit,
    updateCompletionStatus: (Todo)-> Unit,
    updateTodo: (Todo) -> Unit,
    viewModel: TodoViewModel = hiltViewModel()
) {
    // Controls whether the input field is visible
    var showInput by remember { mutableStateOf(false) }

    // Observes the current text input state from the ViewModel
    val text by viewModel.text.collectAsState()
    val priority by viewModel.priority.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var todoToEdit by remember { mutableStateOf<Todo?>(null) }

    LaunchedEffect(todoToEdit) {
        todoToEdit?.let {
            viewModel.onTextChanged(it.title)
            viewModel.onPriorityChanged(it.priority)
        }
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Input section for adding a new to-do
            if (showInput) {
                // Text field for entering a new to-do
                OutlinedTextField(
                    value = text,
                    onValueChange = { viewModel.onTextChanged(it) },
                    label = { Text("To-do")}
                )
                // Priority Dropdown field
                Box {
                    Button(
                        onClick = { expanded = true }
                    ) {
                        Text("Priorität: $priority")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Priorität 1") },
                            onClick = {
                                viewModel.onPriorityChanged(1)
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Priorität 2") },
                            onClick = {
                                viewModel.onPriorityChanged(2)
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Priorität 3") },
                            onClick = {
                                viewModel.onPriorityChanged(3)
                                expanded = false
                            }
                        )
                    }
                }

                // The add button is only shown
                // when the text input is not empty
                if(text.isNotEmpty()){
                    Button(onClick = {
                        if (todoToEdit == null) {
                            addTodo(text, priority)
                        }
                        else {
                            updateTodo(
                                todoToEdit!!.copy(
                                    title = text,
                                    priority = priority
                                )
                            )
                            todoToEdit = null
                        }

                        // Clears the input field
                        viewModel.onTextChanged("")
                    }) {
                        Text(
                            if (todoToEdit == null) "Add to list"
                            else "Save"
                        )
                    }
                }
            }

        // Scrollable container for all UI elements
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            // Screen title
            item {
                Text(text = "Todo Screen")
            }

            // Displays all existing to-dos
            items(items = stillTodos, key = { it.id }) { stillTodos->
                TodoItem(
                    stillTodos,
                    onDone = { updateCompletionStatus(it) },
                    onClick = {
                        todoToEdit = it
                        showInput = true
                    }
                )
            }
            // Displays all completed to-dos
            items(items = alreadyDones, key = { it.id }) {alreadyDones ->
                TodoItem(
                    alreadyDones,
                    onDone = { updateCompletionStatus(it)},
                    onClick = {
                        todoToEdit = it
                        showInput = true
                    }
                )
            }

            // Button used to show the input field
            if(!showInput) {
                item {
                    Button(onClick = {
                        showInput = true
                    })
                    { Text("Add To-do.") }
                }
            }
        }
        }
    }
}
