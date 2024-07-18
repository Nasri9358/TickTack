package com.bignerdranch.android.ticktack.di

import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(
            getAllTaskItemsUseCase = get(),
            updateTaskUseCase = get()
        )
    }

    ViewModel<CreateTaskActivityViewModel> {
        CreateTaskActivityViewModel(
            createTaskUseCase = get()
        )
    }

    ViewModel<CreateTaskGroupActivityViewModel> {
        CreateTaskGroupActivityViewModel(
            createTaskUseCase = get()
        )
    }

    viewModel<TaskActivityViewModel> {
        TaskActivityViewModel(
            updateTaskUseCase = get(),
            deleteTaskUseCase = get(),
            getAllTaskGroupsUseCase = get(),
            getTaskGroupByIdUseCase = get()
        )
    }

    viewModel<TaskGroupActivityViewModel> {
        TaskGroupActivityViewModel(
            updateTaskGroupUseCase = get(),
            deleteTaskGroupUseCase = get(),
            getAllTasksUseCase = get(),
            updateTaskUseCase = get(),
            deleteTaskUseCase = get()
        )
    }



}