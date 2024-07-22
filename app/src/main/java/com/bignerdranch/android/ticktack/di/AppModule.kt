package com.bignerdranch.android.ticktack.di

import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskActivityViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskGroupActivityViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.FavouriteTasksFragmentViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.MainFragmentViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskActivityViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskGroupActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(
            getAllTaskItemsUseCase = get(),
            updateTaskUseCase = get()
        )
    }

    viewModel<FavouriteTasksFragmentViewModel> {
        FavouriteTasksFragmentViewModel(
            getFavouriteTasksUseCase = get(),
            updateTaskUseCase = get()
        )
    }

    viewModel<CreateTaskActivityViewModel> {
        CreateTaskActivityViewModel(
            createTaskUseCase = get()
        )
    }

    viewModel<CreateTaskGroupActivityViewModel> {
        CreateTaskGroupActivityViewModel(
            createTaskGroupUseCase = get()
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