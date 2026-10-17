package com.example.to_do_list

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.to_do_list.data.AppDatabase
import com.example.to_do_list.data.Todo
import com.example.to_do_list.data.TodoDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: TodoDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.todoDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertTodo_savesTodoInDatabase() = runBlocking {
        // Arrange
        val todo = Todo(
            title = "Lernen",
            done = false,
            priority = 1
        )

        // Act
        dao.insert(todo)

        // Assert
        // ".first" only works reliably because there is just one element in the db
        val todos = dao.getAll().first()

        assertEquals(1, todos.size)
        assertEquals("Lernen", todos[0].title)
        assertEquals(false, todos[0].done)
        assertEquals(1, todos[0].priority)
    }

    @Test
    fun deleteTodo_deleteOutOfDatabase() = runBlocking {
        // Arrange
        // insert the task you want to delete afterward
        val todo = Todo(
            id = 1,
            title = "Kochen",
            done = true,
            priority = 2
        )
        dao.insert(todo)

        // Act
        dao.delete(todo)

        // Assert
        // DB should be empty

        val todos = dao.getAll().first()
        assertEquals(0, todos.size)
    }

    @Test
    fun getAllOpen_onlyGetBackOpen() = runBlocking {
        // Arrange
        val todo1 = Todo(
            id = 1,
            title = "Kochen",
            done = false,
            priority = 1
        )
        val todo2 = Todo(
            id = 2,
            title = "Backen",
            done = true,
            priority = 2
        )
        val todo3 = Todo(
            id = 3,
            title = "Aufräumen",
            done = false,
            priority = 2
        )
        dao.insert(todo1)
        dao.insert(todo2)
        dao.insert(todo3)

        // Act
        val openTodos = dao.getAllOpen().first()

        // Assert
        assertEquals(2, openTodos.size)
        assertEquals(
            setOf("Kochen", "Aufräumen"),
            openTodos.map { it.title }.toSet())
    }

    @Test
    fun getAllClosed_onlyGetBackClosed() = runBlocking {
        // Arrange
        val todo1 = Todo(
            id = 1,
            title = "Kochen",
            done = false,
            priority = 1
        )
        val todo2 = Todo(
            id = 2,
            title = "Backen",
            done = true,
            priority = 2
        )
        val todo3 = Todo(
            id = 3,
            title = "Aufräumen",
            done = false,
            priority = 2
        )
        dao.insert(todo1)
        dao.insert(todo2)
        dao.insert(todo3)

        // Act
        val closedTodos = dao.getAllClosed().first()

        // Assert
        assertEquals(1, closedTodos.size)
        assertEquals(
            setOf("Backen"),
            closedTodos.map { it.title }.toSet())
    }

    @Test
    fun update_savesChanges() = runBlocking {
        // Arrange
        val todo1 = Todo(
            id = 1,
            title = "Kochen",
            done = false,
            priority = 1)
        dao.insert(todo1)
        // same task but done:
        val todo2 = Todo(
            id = 1,
            title = "Kochen",
            done = true,
            priority = 1)

        // Act
        dao.update(todo2)

        // Assert
        val todos = dao.getAll().first()
        assertEquals("Kochen",todos[0].title)
        assertEquals(1,todos[0].id)
        assertEquals(true,todos[0].done)
    }
}