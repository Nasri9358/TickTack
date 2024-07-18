package com.bignerdranch.android.ticktack.domain.usecase.taskUseCases

import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.repository.TaskRepository

class CreateTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(task: Task) {
        taskRepository.createTask(task)
    }
}