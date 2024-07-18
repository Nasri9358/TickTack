package com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase

import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.domain.repository.TaskGroupRepository

class GetTaskGroupByIdUseCase(private val taskGroupRepository: TaskGroupRepository) {
    suspend fun execute(id: Int): TaskGroup {
        return taskGroupRepository.getTaskGroupById(id)
    }
}