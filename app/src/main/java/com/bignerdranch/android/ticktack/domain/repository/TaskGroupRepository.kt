package com.bignerdranch.android.ticktack.domain.repository

import com.bignerdranch.android.ticktack.domain.models.TaskGroup

interface TaskGroupRepository {
    suspend fun createTaskGroup(taskGroup: TaskGroup)
    suspend fun deleteTaskGroup(taskGroup: TaskGroup)
    suspend fun updateTaskGroup(taskGroup: TaskGroup)

    suspend fun getAllTaskGroup(): List<TaskGroup>
    suspend fun getTaskGroupById(id: Int): TaskGroup
}