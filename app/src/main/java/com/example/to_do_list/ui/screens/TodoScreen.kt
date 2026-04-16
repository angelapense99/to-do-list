package com.example.to_do_list.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.to_do_list.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    var showInput by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("test") }

    Scaffold { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(text = "Todo Screen")
            }
            items(viewModel.todos){todo->
                Text(todo.title)
            }
            item {
                Button(onClick = {showInput = true})
                { Text("Add To-do.") }
            }
            if (showInput) {
                item {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text("Neues Todo") }
                    )
                }
            }
        }

    }
}
