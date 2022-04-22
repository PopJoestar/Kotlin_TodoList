package com.popjoestar.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.popjoestar.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!;


    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoList: MutableList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        binding.rvTodoItems.layoutManager = layoutManager

        todoAdapter = TodoAdapter(mutableListOf())

        binding.rvTodoItems.adapter = todoAdapter

        with(binding) {
            btnAddTodo.setOnClickListener {
                val newTodoTitle = etTodoTitle.text.toString()

                if (newTodoTitle.isNotBlank()) {
                    val todo = Todo(newTodoTitle)
                    todoAdapter.addTodo(todo)
                    etTodoTitle.text.clear()
                }
            }

            btnDeleteTodos.setOnClickListener {
                todoAdapter.deleteDoneTodos()
            }
        }
    }

}