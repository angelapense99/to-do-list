package com.example.to_do_list.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.to_do_list.data.Todo
import com.example.to_do_list.ui.component.TodoItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.LaunchedEffect


/**
 * Main screen of the to-do application.
 *
 * Displays:
 * - A list of open to-dos and completed to-dos
 * - An input field for creating and editing to-dos
 * - A dropdown menu for selecting the priority (1–3)
 * - Buttons for adding, saving, and deleting to-dos
 *
 * The screen receives its UI state and callbacks as parameters.
 * State changes and to-do operations are handled through the callbacks.
 *
 * @param states Current UI state, including the to-do lists, input text, and priority
 * @param callbacks Callbacks for adding, editing, deleting, and completing to-dos,
 *                  as well as updating the input text and priority
 */

@Composable
fun TodoScreen(
    states: TodoScreenState,
    callbacks: TodoScreenCallbacks,
) {

    // Controls whether the input field is visible
    var showInput by remember { mutableStateOf(false) }


    var expanded by remember { mutableStateOf(false) }
    var todoToEdit by remember { mutableStateOf<Todo?>(null) }

    LaunchedEffect(todoToEdit) {
        todoToEdit?.let {
            callbacks.onTextChanged(it.title)
            callbacks.onPriorityChanged(it.priority)
        }
    }

    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            // Input section for adding a new to-do
            if (showInput) {
                // Text field for entering a new to-do
                OutlinedTextField(
                    value = states.text,
                    onValueChange = { callbacks.onTextChanged(it) },
                    label = { Text("To-do")}
                )
                // Priority Dropdown field
                Box {
                    Button(
                        onClick = { expanded = true }
                    ) {
                        Text("Priorität: ${states.priority}")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Priorität 1") },
                            onClick = {
                                callbacks.onPriorityChanged(1)
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Priorität 2") },
                            onClick = {
                                callbacks.onPriorityChanged(2)
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Priorität 3") },
                            onClick = {
                                callbacks.onPriorityChanged(3)
                                expanded = false
                            }
                        )
                    }
                }

                // Delete Button
                if (todoToEdit != null) {
                    Button(
                        onClick = {
                            callbacks.deleteTodo(todoToEdit!!)
                            todoToEdit = null
                            callbacks.onTextChanged("")
                        }
                    ) {
                        Text("Delete")
                    }
                }

                // The add button is only shown when the text input is not empty
                if(states.text.isNotEmpty()){
                    Button(onClick = {
                        //new task
                        if (todoToEdit == null) {
                            callbacks.addTodo(states.text, states.priority)
                        }
                        //edit task
                        else {
                            callbacks.updateTodo(
                                todoToEdit!!.copy(
                                    title = states.text,
                                    priority = states.priority
                                )
                            )
                            todoToEdit = null
                        }

                        // Clears the input field
                        callbacks.onTextChanged("")
                        showInput = false
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

            // Displays all existing to-dos
            items(items = states.stillTodos, key = { it.id }) { stillTodo ->
                TodoItem(
                    stillTodo,
                    onDone = { callbacks.updateCompletionStatus(it) },
                    onClick = {
                        todoToEdit = it
                        showInput = true
                    }
                )
            }
            // Displays all completed to-dos
            items(items = states.alreadyDones, key = { it.id }) {alreadyDone ->
                TodoItem(
                    alreadyDone,
                    onDone = { callbacks.updateCompletionStatus(it)},
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

data class TodoScreenState(
    val stillTodos: List<Todo>,
    val alreadyDones: List<Todo>,
    val text: String,
    val priority: Int,
)

interface TodoScreenCallbacks{
    fun addTodo (title: String, priority: Int)
    fun updateCompletionStatus (todo: Todo)
    fun updateTodo (todo: Todo)
    fun deleteTodo (todo: Todo)
    fun onTextChanged(title: String)
    fun onPriorityChanged(newPriority: Int)
}
