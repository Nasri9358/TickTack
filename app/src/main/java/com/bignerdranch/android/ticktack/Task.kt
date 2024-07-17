package com.bignerdranch.android.ticktack

import android.content.res.AssetFileDescriptor
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
            val title: String,
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val isCompleted: Boolean =false
)