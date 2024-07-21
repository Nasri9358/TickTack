package com.bignerdranch.android.ticktack.data.repository

import com.bignerdranch.android.ticktack.data.room.Mapper
import com.bignerdranch.android.ticktack.data.room.dao.TaskDao
import kotlin.math.tan

class TaskRepositoryImpl(private val taskDao: TaskDao): TaskRepository {
    private val mapper = Mapper()

    override suspend fun addTask(task: Task) {
        taskDao.addTask(mapper.taskToTaskEntity(task))
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(mapper.taskToTaskEntity(task))
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(mapper.taskToTaskEntity(task))
    }

    override suspend fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks().map {
            mapper.taskEntityToTask(it)
        }
    }

    override suspend fun getAllTasksFromGroup(taskGroupId: Int?): List<Task> {
        return taskDao.getAllTasks(taskGroupId).map {
            mapper.taskEntityToTask(it)
        }
    }

    override suspend fun getFavouriteTasks(): List<Task> {
        return taskDao.getFavouriteTasks().map {
            mapper.taskEntityToTask(it)
        }
    }

    override suspend fun getTaskById(id: Int): Task {
        return mapper.taskEntityToTask(taskDao.getTaskById(id))
    }
}