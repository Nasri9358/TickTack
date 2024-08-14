package com.bignerdranch.android.ticktack.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ticktack.data.repository.TaskRepositoryImpl
import com.bignerdranch.android.ticktack.domain.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskActivityViewModel(
private val createTaskUseCase: TaskRepositoryImpl
): ViewModel() {
    private val dispatcher = Dispatchers.IO

    fun createTask(task: Task) {
        viewModelScope.launch(dispatcher) {
            createTaskUseCase.execute(task)
        }
    }
}