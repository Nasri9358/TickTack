package com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase

import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.domain.repository.TaskGroupRepository

class UpdateTaskGroupUseCase(private val taskGroupRepository: TaskGroupRepository) {
    suspend fun execute(taskGroup: TaskGroup) {
        taskGroupRepository.updateTaskGroup(taskGroup)
    }
}