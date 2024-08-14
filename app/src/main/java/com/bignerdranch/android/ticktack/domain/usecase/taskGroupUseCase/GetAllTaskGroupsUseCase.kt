package com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase

import com.bignerdranch.android.ticktack.data.repository.TaskRepositoryImpl
import com.bignerdranch.android.ticktack.domain.models.TaskGroup

class GetAllTaskGroupsUseCase(private val taskGroupRepository: TaskRepositoryImpl) {
    suspend fun execute(): List<TaskGroup> {
        return taskGroupRepository.getAllTaskGroups()
    }
}