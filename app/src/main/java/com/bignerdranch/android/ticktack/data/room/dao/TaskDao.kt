package com.bignerdranch.android.ticktack.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bignerdranch.android.ticktack.data.room.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun addTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

    @Query ("SELECT * FROM task_table WHERE taskGroupId IS NULL ORDER BY isCompleted ASC")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query ("SELECT * FROM task_table WHERE taskGroupId=:taskGroupId ORDER BY isCompleted ASC")
    suspend fun getAllTasks(taskGroupId: Int?): List<TaskEntity>

    @Query ("SELECT * FROM task_table WHERE isFavourite=1 ORDER BY isCompleted ASC")
    suspend fun getFavouriteTasks(): List<TaskEntity>

    @Query ("SELECT * FROM task_table WHERE id=:id")
    suspend fun  getTaskById(Id: Int): TaskEntity
}