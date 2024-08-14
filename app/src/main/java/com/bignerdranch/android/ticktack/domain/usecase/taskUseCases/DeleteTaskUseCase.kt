package com.bignerdranch.android.ticktack.domain.usecase.taskUseCases

import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import com.bignerdranch.android.ticktack.domain.models.Task


class DeleteTaskUseCase(private val taskRepository: TaskGroupRepositoryImpl) {
    suspend fun execute(task: Task) {
        taskRepository.deleteTask(task)
    }
}