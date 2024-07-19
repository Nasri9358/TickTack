package com.bignerdranch.android.ticktack.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.DeleteTaskGroupUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.UpdateTaskGroupUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.DeleteTaskUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetAllTasksUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskGroupActivityViewModel(
    private val updateTaskGroupUseCase: UpdateTaskGroupUseCase,
    private val deleteTaskGroupUseCase: DeleteTaskGroupUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
): ViewModel(), TaskViewModel {
    val task = MutableLiveData<List<Task>>()
    private val dispatcher = Dispatchers.IO

    fun updateTaskGroup(taskGroup: TaskGroup) {
        viewModelScope.launch(dispatcher) {
            updateTaskGroupUseCase.execute(taskGroup)
        }
    }

    fun deleteTaskGroup(taskGroup: TaskGroup) {
        viewModelScope.launch(dispatcher) {
            deleteTaskGroupIds().join()
            deleteTaskGroupUseCase.execute(taskGroup)
        }
    }

    fun getAllTaskById(taskGroupId: Int) {
        viewModelScope.launch(dispatcher) {
            tasks.postValue(getAllTasksUseCase.execute(taskGroupId))
        }
    }

    fun deleteTasks() {
        viewModelScope.launch(dispatcher) {
            task.value?.forEach{deleteTaskUseCase.execute(it)}
        }
    }

    private suspend fun deleteTaskGroupIds() = viewModelScope.launch (dispatcher) {
        task.value?.forEach { updateTaskUseCase.execute(it.copy(taskGroupId = null)) }
    }

    override fun completeTask(task: Task) {
        viewModelScope.launch (dispatcher) {
            updateTaskUseCase.execute(task.copy(isCompleted = !task.isCompleted))
            getAllTaskById(task.taskGroupId!!)
        }
    }
}