package com.bignerdranch.android.ticktack.domain.usecase

import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import com.bignerdranch.android.ticktack.domain.models.TaskItem
import com.bignerdranch.android.ticktack.domain.repository.TaskGroupRepository

class GetAllTaskItemsUseCase(
    private val taskRepository: TaskGroupRepositoryImpl,
    private val taskGroupRepository: TaskGroupRepository,
) {
    suspend fun execute(): List<TaskItem> {
        return taskGroupRepository.getAllTaskGroups() + taskRepository.getAllTasks()
    }
}