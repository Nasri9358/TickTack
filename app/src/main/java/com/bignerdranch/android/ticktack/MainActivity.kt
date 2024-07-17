package com.bignerdranch.android.ticktack

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        val adapter =  TaskListAdapter()

        val recyclerView =findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter =adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let { adapter.submitList(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this, TaskDetailActivity::class.java)
            startActivity(intent)
        }
    }
}