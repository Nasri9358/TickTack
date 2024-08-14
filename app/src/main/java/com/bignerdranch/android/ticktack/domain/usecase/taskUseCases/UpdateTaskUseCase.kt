package com.bignerdranch.android.ticktack.domain.usecase.taskUseCases

import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import com.bignerdranch.android.ticktack.domain.models.Task

class UpdateTaskUseCase(private val taskRepository: TaskGroupRepositoryImpl) {
    suspend fun execute(task: Task) {
        taskRepository.updateTask(task)
    }
}