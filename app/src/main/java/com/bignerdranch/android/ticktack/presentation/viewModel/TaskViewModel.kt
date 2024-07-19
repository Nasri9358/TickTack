package com.bignerdranch.android.ticktack.presentation.viewModel

import com.bignerdranch.android.ticktack.domain.models.Task

interface TaskViewModel {
    fun completeTask(task: Task)
}