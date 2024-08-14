package com.bignerdranch.android.ticktack.presentation.view.taskGroupView

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import com.bignerdranch.android.ticktack.data.room.MainDatabase
import com.bignerdranch.android.ticktack.databinding.ActivityTaskGroupBinding
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.DeleteTaskGroupUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.UpdateTaskGroupUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.DeleteTaskUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetAllTasksUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TASK_GROUP_NAME_EXTRA
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.view.taskView.CreateTaskActivity
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskGroupActivityViewModel

class TaskGroupActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTaskGroupBinding.inflate(layoutInflater) }
    private val recycler by lazy { binding.rvTaskGroupTasks }
    private val taskGroup by lazy { intent.extras?.getSerializable(TASK_GROUP_NAME_EXTRA) as TaskGroup }

    // Получите базу данных и DAO
    private val database by lazy { MainDatabase.getDatabase(this) }
    private val taskGroupDao by lazy { database.TaskGroupDao() }

    // Создайте репозиторий с использованием DAO
    private val repository by lazy { TaskGroupRepositoryImpl(taskGroupDao) }

    // Создайте UseCase объекты
    private val updateTaskGroupUseCase by lazy { UpdateTaskGroupUseCase(repository) }
    private val deleteTaskGroupUseCase by lazy { DeleteTaskGroupUseCase(repository) }
    private val getAllTasksUseCase by lazy { GetAllTasksUseCase(repository) }
    private val updateTaskUseCase by lazy { UpdateTaskUseCase(repository) }
    private val deleteTaskUseCase by lazy { DeleteTaskUseCase(repository) }

    // ViewModel для TaskGroupActivityViewModel
    private val taskGroupActivityViewModel by lazy {
        TaskGroupActivityViewModel(
            updateTaskGroupUseCase = updateTaskGroupUseCase,
            deleteTaskGroupUseCase = deleteTaskGroupUseCase,
            getAllTasksUseCase = getAllTasksUseCase,
            updateTaskUseCase = updateTaskUseCase,
            deleteTaskUseCase = deleteTaskUseCase
        )
    }
    /*// ViewModel для TaskGroupActivityViewModel
    private val taskGroupActivityViewModel by lazy {
        TaskGroupActivityViewModel(
            updateTaskGroupUseCase = UpdateTaskGroupUseCase(repository),
            deleteTaskGroupUseCase = DeleteTaskGroupUseCase(repository),
            getAllTasksUseCase = GetAllTasksUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository),
            deleteTaskUseCase = DeleteTaskUseCase(repository)
        )
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = TaskAdapter(OnItemClickListener(this, taskGroupActivityViewModel))

        recycler.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        taskGroupActivityViewModel.tasks.observe(this) {
            adapter.updateList(it)
            binding.tvIsEmptyList.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.btnDeleteTaskGroup.setOnClickListener { deleteTaskGroup() }
        binding.btnAddTaskToGroup.setOnClickListener { addTask() }

        setTaskGroupData()
    }

    override fun onPause() {
        super.onPause()
        clearEditTextFocus()
        updateTaskGroup(taskGroup)
    }

    override fun onResume() {
        super.onResume()
        taskGroupActivityViewModel.getAllTasksById(taskGroup.id)
    }

    private fun updateTaskGroup(newTaskGroup: TaskGroup) {
        val name = binding.tvTaskGroupName.text.toString()
        val desc = binding.tvTaskGroupDescription.text.toString()

        val updatedTaskGroup = if (newTaskGroup.name != name || newTaskGroup.description != desc) {
            newTaskGroup.copy(name = name, description = desc)
        } else {
            newTaskGroup
        }

        taskGroupActivityViewModel.updateTaskGroup(updatedTaskGroup)
        setTaskGroupData()
    }

    private fun addTask() {
        val intent = Intent(this, CreateTaskActivity::class.java).apply {
            putExtra("taskGroupId", taskGroup.id)
        }
        startActivity(intent)
    }

    private fun setTaskGroupData() {
        binding.tvTaskGroupName.setText(taskGroup.name)
        binding.tvTaskGroupDescription.setText(taskGroup.description)
    }

    private fun deleteTaskGroup() {
        taskGroupActivityViewModel.deleteTaskGroup(taskGroup)
        finish()
    }

    private fun clearEditTextFocus() {
        binding.tvTaskGroupName.clearFocus()
        binding.tvTaskGroupDescription.clearFocus()
    }
}