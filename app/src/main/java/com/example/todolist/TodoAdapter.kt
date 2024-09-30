package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoTextView: TextView = itemView.findViewById(R.id.todo_text_view)
        val deleteButton: Button = itemView.findViewById(R.id.delete_button)
        val todoCheckBox: CheckBox = itemView.findViewById(R.id.todo_checkbox)

        init {
            todoCheckBox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    todos[position].isCompleted = isChecked // Update the completion status
                    todoTextView.paint.isStrikeThruText = isChecked // Strike through text if completed
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.todoTextView.text = todo.task
        holder.todoCheckBox.isChecked = todo.isCompleted // Set checkbox state
        holder.deleteButton.setOnClickListener {
            onDelete(position) // Handle delete action
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
