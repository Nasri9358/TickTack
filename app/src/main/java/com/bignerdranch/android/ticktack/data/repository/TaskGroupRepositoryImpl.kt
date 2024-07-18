package com.bignerdranch.android.ticktack.data.repository

import com.bignerdranch.android.ticktack.data.room.Mapper
import com.bignerdranch.android.ticktack.data.room.dao.TaskGroupDao

class TaskGroupRepositoryImpl(private val taskGroupDao: TaskGroupDao): TaskGroupRepository {
    private val mapper = Mapper()

    override suspend fun addTaskGroup(taskGroup: TaskGroup) {
        taskGroupDao.addTaskGroup(mapper.taskGroupToTaskGroupEntity(taskGroup))
    }

    override suspend fun deleteTaskGroup(taskGroup: TaskGroup) {
        taskGroupDao.deleteTaskGroup(mapper.taskGroupToTaskGroupEntity(taskGroup))
    }

    override suspend fun updateTaskGroup(taskGroup: TaskGroup) {
        taskGroupDao.updateTaskGroup(mapper.taskGroupToTaskGroupEntity(taskGroup))
    }

    override suspend fun getAllTaskGroups(): List<TaskGroup> {
        return taskGroupDao.getAllTaskGroups().map {
            mapper.taskGroupEntityToTaskGroup(it)
        }
    }

    override suspend fun getTaskGroupId(id: Int): TaskGroup {
        return mapper.taskGroupEntityToTaskGroup(taskGroupDao.getTaskGroupById(id))
    }
}