package com.bignerdranch.android.ticktack.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.ticktack.data.room.entity.TaskGroupEntity

@Dao
interface TaskGroupDao {

    @Insert
    suspend fun addTaskGroup(taskGroupEntity: TaskGroupEntity)

    @Update
    suspend fun updateTaskGroup(taskGroupEntity: TaskGroupEntity)

    @Delete
    suspend fun deleteTaskGroup(taskGroupEntity: TaskGroupEntity)

    @Query ("SELECT * from task_group")
    suspend fun getAllTaskGroups(): List<TaskGroupEntity>

    @Query ("SELECT * from task_group WHERE id=:id")
    suspend fun getTaskGroupById(id: Int): TaskGroupEntity
}