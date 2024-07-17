package com.bignerdranch.android.ticktack

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class TaskDetailActivity : AppCompatActivity() {
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val saveButton = findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            val title = findViewById<EditText>(R.id.title_EditText).text.toString()
            val description = findViewById<EditText>(R.id.descriptionEditText).text.toString()
            val dueDate = System.currentTimeMillis()
            val priority = 1

            val task = Task(title = title, description = description, dueDate = dueDate, priority = priority)
            taskViewModel.insertTask(task)

            finish()
        }
    }
}