package com.bignerdranch.android.ticktack.presentation.view.taskGroupView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.ticktack.R
import com.bignerdranch.android.ticktack.databinding.ActivityCreateTaskGroupBinding
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskGroupActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateTaskGroupActivity : AppCompatActivity() {
    private val binding by lazy {ActivityCreateTaskGroupBinding.inflate(layoutInflater)}
    private val createTaskGroupActivityViewModel: CreateTaskGroupActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnAddTaskGroup.setOnClickListener {
            val name = binding.inputTaskGroupName.text.toString()
            val description = binding.inputTaskGroupDescription.text.toString()

            if (name.isBlank()) {
                binding.inputTaskGroupName.error = getString(R.string.enterName)
                return@setOnClickListener
            }

            addTaskGroup(name, description)
        }

        setContentView(binding.root)
    }

    private fun addTaskGroup(name: String, description: String) {
        createTaskGroupActivityViewModel.createTaskGroup(TaskGroup(name, description))
        finish()
    }
}