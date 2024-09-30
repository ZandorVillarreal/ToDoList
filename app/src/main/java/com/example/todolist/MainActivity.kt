package com.example.todolist

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var todoInput: EditText
    private lateinit var todoList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoInput = findViewById(R.id.todoInput)
        todoList = findViewById(R.id.todoList)
    }

    fun addTodoItem(view: View) {
        val todoText = todoInput.text.toString()

        if (todoText.isNotEmpty()) {
            val todoItem = TextView(this)
            todoItem.text = todoText
            todoItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            todoList.addView(todoItem)
            todoInput.text.clear() // Clear the input field
        }
    }
}
