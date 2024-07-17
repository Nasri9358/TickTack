package com.bignerdranch.android.ticktack.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_group")
data class TaskGroupEntity (
    val name: String,
    val description: String,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)