package com.bignerdranch.android.ticktack.domain.repository

import com.bignerdranch.android.ticktack.domain.models.Task

interface TaskRepository {
    suspend fun createTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)

    suspend fun getAllTasks(): List<Task>
    suspend fun getAllTasksFromGroup(taskGroupId: Int? ): List <Task>
    suspend fun getFavouriteTasks(): List<Task>
    suspend fun getTaskById(id: Int): Task
}