package com.bignerdranch.android.ticktack.presentation.view.taskGroupView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.ticktack.R
import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import com.bignerdranch.android.ticktack.data.room.MainDatabase
import com.bignerdranch.android.ticktack.databinding.ActivityCreateTaskGroupBinding
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskGroupActivityViewModel

class CreateTaskGroupActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCreateTaskGroupBinding.inflate(layoutInflater) }

    // Получите базу данных и DAO
    private val database by lazy { MainDatabase.getDatabase(this) }
    private val taskGroupDao by lazy { database.TaskGroupDao() }

    // Создайте репозиторий с использованием DAO
    private val repository by lazy { TaskGroupRepositoryImpl(taskGroupDao) }

    // ViewModel для TaskGroupActivityViewModel
    private val createTaskGroupActivityViewModel: CreateTaskGroupActivityViewModel by lazy {
        CreateTaskGroupActivityViewModel(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAddTaskGroup.setOnClickListener {
            val name = binding.inputTaskGroupName.text.toString()
            val description = binding.inputTaskGroupDescription.text.toString()

            if (name.isBlank()) {
                binding.inputTaskGroupName.error = getString(R.string.enterName)
                return@setOnClickListener
            }

            addTaskGroup(name, description)
        }
    }

    private fun addTaskGroup(name: String, description: String) {
        createTaskGroupActivityViewModel.createTaskGroup(TaskGroup(name, description))
        finish()
    }
}
