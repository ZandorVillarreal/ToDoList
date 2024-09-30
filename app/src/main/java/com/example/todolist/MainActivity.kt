package com.example.todolist

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoInput: EditText
    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var todoAdapter: TodoAdapter
    private val todoList = mutableListOf<Todo>()
    private var nextId = 0 // Counter for unique IDs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoInput = findViewById(R.id.todoInput)
        todoRecyclerView = findViewById(R.id.todoRecyclerView)

        todoRecyclerView.layoutManager = LinearLayoutManager(this)

        todoAdapter = TodoAdapter(todoList) { position ->
            deleteTodoItem(position)
        }

        todoRecyclerView.adapter = todoAdapter
    }

    fun addTodoItem(view: View) {
        val todoText = todoInput.text.toString()

        if (todoText.isNotEmpty()) {
            val newTodo = Todo(nextId++, todoText) // Create a new Todo object with a unique ID
            todoList.add(newTodo) // Add it to the list
            todoAdapter.notifyItemInserted(todoList.size - 1) // Notify adapter about the new item
            todoInput.text.clear() // Clear the input field
        }
    }

    private fun deleteTodoItem(position: Int) {
        todoList.removeAt(position) // Remove the item from the list
        todoAdapter.notifyItemRemoved(position) // Notify adapter about the removed item
    }
}
