package com.bignerdranch.android.ticktack.domain.usecase.taskUseCases

import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.repository.TaskRepository

class GetAllTasksUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(): List<Task> {
        return taskRepository.getAllTasks()
    }

    suspend fun execute(taskGroupId: Int?): List<Task> {
        return taskRepository.getAllTasksFromGroup(taskGroupId)
    }
}