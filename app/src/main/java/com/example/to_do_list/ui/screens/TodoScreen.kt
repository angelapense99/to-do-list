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
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.to_do_list.data.Todo
import com.example.to_do_list.ui.component.TodoItem
import com.example.to_do_list.viewmodel.TodoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoScreen(
    todos: List<Todo>,
    addTodo: (String) -> Unit,
    delete: (Todo)-> Unit,
    viewModel: TodoViewModel = hiltViewModel()
) {
    var showInput by remember { mutableStateOf(false) }
    val text by viewModel.text.collectAsState()

    Scaffold { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(text = "Todo Screen")
            }
            items(items = todos, key = { it.id }) { todo->
                TodoItem(todo, onDelete = { delete(it) })
            }
            if(!showInput) {
                item {
                    Button(onClick = {
                        showInput = true
                    })
                    { Text("Add To-do.") }
                }
            }
            if (showInput) {
                item {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { viewModel.onTextChanged(it) },
                    )
                    if(text.isNotEmpty()){
                        Button(onClick = {
                            addTodo(text)
                            viewModel.onTextChanged("")
                        })
                        { Text("Add to list") }
                    }
                }
            }

        }

    }
}
