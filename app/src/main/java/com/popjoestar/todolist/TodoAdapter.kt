package com.popjoestar.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.popjoestar.todolist.databinding.ItemTodoBinding


class TodoAdapter(private val todos: MutableList<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val todoBinding:ItemTodoBinding) : RecyclerView.ViewHolder(todoBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TodoViewHolder(
            binding
        )
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]

        with (holder) {
            with(todoBinding) {
                tvTodoTitle.text = currentTodo.title
                cbDone.isChecked = currentTodo.isChecked

                toggleStrikeThrough(tvTodoTitle, currentTodo.isChecked)

                cbDone.setOnCheckedChangeListener { _, isChecked ->
                    toggleStrikeThrough(tvTodoTitle, isChecked)
                    currentTodo.isChecked = isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = 0
        }
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.isChecked  }
        notifyDataSetChanged()

    }

}