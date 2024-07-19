package com.bignerdranch.android.ticktack.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.models.TaskItem
import com.bignerdranch.android.ticktack.domain.usecase.GetAllTaskItemsUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetAllTasksUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getAllTasksItemsUseCase: GetAllTaskItemsUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel(), TaskViewModel  {
    val taskItems = MutableLiveData<List<TaskItem>>()
    private val dispatcher = Dispatchers.IO

    fun getAllTaskItems() {
        viewModelScope.launch(dispatcher) {
            taskItems.postValue(getAllTasksItemsUseCase.execute())
        }
    }

    override fun completeTask(task: Task) {
        viewModelScope.launch(dispatcher) {
            updateTaskUseCase.execute(task.copy(isCompleted = !task.isCompleted))
            getAllTaskItems()
        }
    }
}