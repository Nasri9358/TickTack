package com.bignerdranch.android.ticktack.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.CreateTaskGroupUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskGroupActivityViewModel(
    private val createTaskGroupUseCase: CreateTaskGroupUseCase
) : ViewModel() {
    private val dispatcher = Dispatchers.IO

    fun createTaskGroup(taskGroup: TaskGroup) {
        viewModelScope.launch(dispatcher) {
            createTaskGroupUseCase.execute(taskGroup)
        }
    }
}