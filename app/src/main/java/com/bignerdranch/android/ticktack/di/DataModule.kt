package com.bignerdranch.android.ticktack.di

import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import org.koin.dsl.module
import com.bignerdranch.android.ticktack.data.repository.TaskRepositoryImpl
import com.bignerdranch.android.ticktack.data.room.MainDatabase
import com.bignerdranch.android.ticktack.data.room.dao.TaskDao
import com.bignerdranch.android.ticktack.data.room.dao.TaskGroupDao
import com.bignerdranch.android.ticktack.domain.repository.TaskGroupRepository
import com.bignerdranch.android.ticktack.domain.repository.TaskRepository

val dataModule = module {
    single<MainDatabase> {
        MainDatabase.getDatabase(context = get())
    }

    single<TaskDao> {
        get<MainDatabase>().TaskDao()
    }

    single<TaskGroupDao> {
        get<MainDatabase>().TaskGroupDao()
    }

    single<TaskRepository> {
        TaskRepositoryImpl(taskDao = get())
    }

    single<TaskGroupRepository>{
        TaskGroupRepositoryImpl(taskGroupDao = get())
    }
}