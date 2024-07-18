package com.bignerdranch.android.ticktack.domain.usecase.taskUseCases

import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.repository.TaskRepository

class GetTaskByIdUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(id: Int): Task {
        return taskRepository.getTaskById(id)
    }
}