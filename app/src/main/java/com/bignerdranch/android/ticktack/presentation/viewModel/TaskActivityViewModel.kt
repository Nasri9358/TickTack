package com.bignerdranch.android.ticktack.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.GetAllTaskGroupsUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.GetTaskGroupByIdUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.DeleteTaskUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TaskActivityViewModel(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getTaskGroupByIdUseCase: GetTaskGroupByIdUseCase,
    private val getAllTaskGroupsUseCase: GetAllTaskGroupsUseCase
) : ViewModel() {
    private val taskGroups = MutableLiveData<List<TaskGroup>>()
    private val dispatcher = Dispatchers.IO

    fun updateTask(task: Task) {
        viewModelScope.launch(dispatcher) {
            updateTaskUseCase.execute(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(dispatcher) {
            deleteTaskUseCase.execute(task)
        }
    }

    fun grtTaskGroupsNames() = if (taskGroups.value != null) {
        taskGroups.value!!.map { it.id }
    } else {
        listOf()
    }

    suspend fun getTaskGroupById(id: Int ) = viewModelScope.async(dispatcher) {
        getTaskGroupByIdUseCase.execute(id)
    }

    private fun getAllTaskGroups() {
        viewModelScope.launch(dispatcher) {
            taskGroups.postValue(getAllTaskGroupsUseCase.execute())
        }
    }

    init {
        getAllTaskGroups()
    }
}
