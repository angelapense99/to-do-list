package com.example.to_do_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_do_list.data.TodoRepository
import com.example.to_do_list.ui.screens.TodoScreen
import com.example.to_do_list.ui.theme.TodolistTheme
import com.example.to_do_list.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewmodel = TodoViewModel(TodoRepository())
        setContent {
            TodolistTheme {
                TodoScreen(viewmodel)
                }
            }
        }
    }

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodolistTheme {
        Greeting("Android")
    }
}
*/
