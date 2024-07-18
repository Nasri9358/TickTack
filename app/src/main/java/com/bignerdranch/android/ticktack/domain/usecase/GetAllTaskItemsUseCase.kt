package com.bignerdranch.android.ticktack.domain.usecase

import com.bignerdranch.android.ticktack.domain.models.TaskItem
import com.bignerdranch.android.ticktack.domain.repository.TaskGroupRepository
import com.bignerdranch.android.ticktack.domain.repository.TaskRepository

class GetAllTaskItemsUseCase (
    private val taskRepository: TaskRepository,
    private val taskGroupRepository: TaskGroupRepository,
    ) {
    suspend fun execute(): List <TaskItem> {
        return taskGroupRepository.getAllTaskGroup() + taskRepository.getAllTasks()
    }
}