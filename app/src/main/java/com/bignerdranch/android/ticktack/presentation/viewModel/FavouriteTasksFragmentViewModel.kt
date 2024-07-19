package com.bignerdranch.android.ticktack.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetFavouriteTasksUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteTasksFragmentViewModel(
    private val getFavouriteTasksUseCase: GetFavouriteTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) : ViewModel(), TaskViewModel {
    val favouriteTasks = MutableLiveData<List<Task>>()
    private val dispatcher = Dispatchers.IO

    fun getFavouriteTasks() {
        viewModelScope.launch(dispatcher) {
            favouriteTasks.postValue(getFavouriteTasksUseCase.execute())
        }
    }

    override fun completeTask(task: Task) {
        viewModelScope.launch(dispatcher) {
            updateTaskUseCase.execute(task.copy(isCompleted = !task.isCompleted))
            getFavouriteTasks()
        }
    }
}