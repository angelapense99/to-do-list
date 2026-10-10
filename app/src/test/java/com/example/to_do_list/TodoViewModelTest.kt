package com.example.to_do_list
import com.example.to_do_list.data.Todo
import com.example.to_do_list.viewmodel.TodoViewModel
import com.example.to_do_list.viewmodel.UserRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: UserRepository
    private lateinit var viewModel: TodoViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        repository = mockk(relaxed = true)
        viewModel = TodoViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `addTodo inserts todo with correct values`() = runTest(testDispatcher) {
        // Arrange
        val title = "Lernen"
        val priority = 1

        // Act
        viewModel.addTodo(title, priority)
        advanceUntilIdle()

        // Assert
        coVerify {
            repository.insert(
                Todo(
                    title = "Lernen",
                    done = false,
                    priority = 1
                )
            )
        }
    }
}

