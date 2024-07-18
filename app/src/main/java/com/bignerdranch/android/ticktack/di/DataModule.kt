package com.bignerdranch.android.ticktack.di

import org.koin.dsl.module
import com.bignerdranch.android.ticktack.data.repository.TaskRepositoryImpl
import com.bignerdranch.android.ticktack.data.room.MainDatabase
import com.bignerdranch.android.ticktack.data.room.dao.TaskDao
import com.bignerdranch.android.ticktack.data.room.dao.TaskGroupDao

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

    single<TaskGroupRepository> {
        TaskRepositoryImpl(taskGroupDao = get())
    }
 }