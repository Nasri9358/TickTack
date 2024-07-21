package com.bignerdranch.android.ticktack.data.room

import com.bignerdranch.android.ticktack.data.room.entity.TaskEntity
import com.bignerdranch.android.ticktack.data.room.entity.TaskGroupEntity

class Mapper {
    fun taskToTaskEntity(task: Task): TaskEntity {
        return TaskEntity(
            task.title,
            task.description,
            task.isFavourite,
            task.isCompleted,
            task.completionDateInMills,
            task.taskGroupId,
            task.id,
        )
    }

    fun taskEntityToTask(taskEntity: TaskEntity): Task {
        return Task(
            taskEntity.title,
            taskEntity.description,
            taskEntity.isFavourite,
            taskEntity.isCompleted,
            taskEntity.completionDateInMills,
            taskEntity.taskGroupId,
            taskEntity.id,
        )
    }

    fun taskGroupToTaskGroupEntity(taskGroup: TaskGroup): TaskGroupEntity {
        return TaskGroupEntity(taskGroup.name, taskGroup.description, taskGroup.id)
    }

    fun taskGroupEntityToTaskGroup(taskGroupEntity: TaskGroupEntity): TaskGroup  {
        return TaskGroup(taskGroupEntity.name, taskGroupEntity.description, taskGroupEntity.id)
    }
}