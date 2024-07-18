package com.bignerdranch.android.ticktack.domain.models

import java.io.Serializable

class TaskGroup (
    val name:String,
    val description: String,
    val id: Int = 0,
) : TaskItem, Serializable
