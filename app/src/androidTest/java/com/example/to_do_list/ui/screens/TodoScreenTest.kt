package com.example.to_do_list.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.to_do_list.MainActivity
import com.example.to_do_list.data.Todo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun todoScreen_titleIsDisplayed() {
        composeTestRule
            .onNodeWithText("Todo Screen")
            .assertIsDisplayed()
    }

    @Test
    fun todoList_displaysAllTodos() {
        // Arrange:
        val todos = listOf(
            Todo(1, "Lernen", false, 1),
            Todo(2, "Einkaufen", false, 2)
        )
        val states = TodoScreenState(
            stillTodos = todos,
            alreadyDones = listOf(),
            text = "",
            priority = 1
        )

        val callbacks = object : TodoScreenCallbacks {
            override fun addTodo(title: String, priority: Int) {}
            override fun updateCompletionStatus(todo: Todo) {}
            override fun updateTodo(todo: Todo) {}
            override fun deleteTodo(todo: Todo) {}
            override fun onTextChanged(title: String) {}
            override fun onPriorityChanged(newPriority: Int) {}
        }

        composeTestRule.setContent {
            TodoScreen(states, callbacks)
        }

        // Assert:
        composeTestRule
            .onNodeWithText("Lernen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Einkaufen")
            .assertIsDisplayed()
    }
}