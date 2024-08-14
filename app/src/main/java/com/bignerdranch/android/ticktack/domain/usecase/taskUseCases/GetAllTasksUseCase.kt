package com.bignerdranch.android.ticktack.domain.usecase.taskUseCases

import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import com.bignerdranch.android.ticktack.domain.models.Task

class GetAllTasksUseCase(private val taskRepository: TaskGroupRepositoryImpl) {
    suspend fun execute(): List<Task> {
        return taskRepository.getAllTasks()
    }

    suspend fun execute(taskGroupId: Int?): List<Task> {
        return taskRepository.getAllTasksFromGroup(taskGroupId)
    }
}