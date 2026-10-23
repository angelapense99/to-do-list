package com.example.to_do_list.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.to_do_list.data.Todo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoScreenTest {
    // Arrange:
    val todos = listOf(
        Todo(1, "Lernen", false, 1),
        Todo(2, "Einkaufen", false, 2)
    )
    val states =  mutableStateOf(TodoScreenState(
        stillTodos = todos,
        alreadyDones = listOf(),
        text = "",
        priority = 1
    )
    )
    val callbacks = object : TodoScreenCallbacks {
        override fun addTodo(title: String, priority: Int) {}
        override fun updateCompletionStatus(todo: Todo) {}
        override fun updateTodo(todo: Todo) {}
        override fun deleteTodo(todo: Todo) {}
        override fun onTextChanged(title: String) {
            states.value = states.value.copy(text = title)
        }
        override fun onPriorityChanged(newPriority: Int) {}
    }
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun todoScreen_titleIsDisplayed() {
        // Arrange:
        composeTestRule.setContent {
            TodoScreen(states.value, callbacks)
        }

        // Assert:
        composeTestRule
            .onNodeWithText("Todo Screen")
            .assertIsDisplayed()
    }

    @Test
    fun todoList_displaysAllTodos() {
        // Arrange
        composeTestRule.setContent {
            TodoScreen(states.value, callbacks)
        }

        // Assert:
        composeTestRule
            .onNodeWithText("Lernen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Einkaufen")
            .assertIsDisplayed()
    }

    @Test
    fun displaysTheInputField() {
        // Arrange
        composeTestRule.setContent {
            TodoScreen(states.value, callbacks)
        }

        // Act:
        composeTestRule
            .onNodeWithText("Add To-do.")
            .performClick()

        // Assert:
        composeTestRule
            .onNodeWithText("To-do")
            .assertIsDisplayed()

    }

    @Test
    fun displays_AddToList_Button() {
        // Arrange
        composeTestRule.setContent {
            TodoScreen(states.value, callbacks)
        }

        // Act:
        composeTestRule
            .onNodeWithText("Add To-do.")
            .performClick()
        composeTestRule
            .onNodeWithText("To-do")
            .performTextInput("Waschen")

        // Assert
        composeTestRule
            .onNodeWithText("Waschen")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Add to list")
            .assertIsDisplayed()

    }
}