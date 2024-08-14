package com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase

import com.bignerdranch.android.ticktack.data.repository.TaskRepositoryImpl
import com.bignerdranch.android.ticktack.domain.models.TaskGroup

class GetTaskGroupByIdUseCase(private val taskGroupRepository: TaskRepositoryImpl) {
    suspend fun execute(id: Int): TaskGroup {
        return taskGroupRepository.getTaskGroupById(id)
    }
}