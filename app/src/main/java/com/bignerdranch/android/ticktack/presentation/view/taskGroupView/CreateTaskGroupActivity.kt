package com.bignerdranch.android.ticktack.presentation.view.taskGroupView

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.inputmethod.InputBinding
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.ticktack.R
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskGroupActivityViewModel
import kotlinx.coroutines.internal.OpDescriptor

class CreateTaskGroupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTaskGroupBinding
    private lateinit var createTaskGroupActivityViewModel: CreateTaskGroupActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskGroupBinding.inflate(layoutInflater)
        createTaskGroupActivityViewModel = getViewModel()

        binding.btnAddTaskGroup.setOnClickListener {
            val name = binding.inputTaskGroupName.text.toString()
            val descriptor = binding.inputTaskGroupDescription.text.toString()

            if (name.isBlank()) {
                binding.inputTaskGroupName.error = getString(R.string.enterName)
                return@setOnClickListener
            }

            addTaskGroup(name,descriptor)
        }

        setContentView(binding.root)
    }

    private fun addTaskGroup(name: String, descriptor: String) {
        createTaskGroupActivityViewModel.createTaskGroup(TaskGroup(name,descriptor))
        finish()
    }
}