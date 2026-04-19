package com.example.to_do_list.ui.screens

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.to_do_list.data.Todo
import com.example.to_do_list.viewmodel.TodoViewModel

@Composable
fun TodoScreen(
    todos: List<Todo>,
    addTodo: (String) -> Unit
) {
    var showInput by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("test") } // eigentlich ins viewmodel
    var showButtonAddTodo by remember { mutableStateOf(true) }

    Scaffold { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(text = "Todo Screen")
            }
            items(todos){todo->
                Text(todo.title)
            }
            if(showButtonAddTodo) {
                item {
                    Button(onClick = {
                        showInput = true
                        showButtonAddTodo = false
                    })
                    { Text("Add To-do.") }
                }
            }
            if (showInput) {
                item {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text("Neues Todo") }
                    )
                    if(text.isNotEmpty()){
                        Button(onClick = {
                            addTodo(text)
                        })
                        { Text("Add to list") }
                    }
                }
            }

        }

    }
}
