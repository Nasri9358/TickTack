package com.bignerdranch.android.ticktack.di

import com.bignerdranch.android.ticktack.domain.usecase.GetAllTaskItemsUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.CreateTaskGroupUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.DeleteTaskGroupUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.GetAllTaskGroupsUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.GetTaskGroupByIdUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskGroupUseCase.UpdateTaskGroupUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.CreateTaskUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.DeleteTaskUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetAllTasksUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetFavouriteTasksUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetTaskByIdUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import org.koin.dsl.module


val domainModule = module {
    factory<CreateTaskUseCase> { CreateTaskUseCase(taskRepository = get()) }
    factory<DeleteTaskUseCase> { DeleteTaskUseCase(taskRepository = get()) }
    factory<UpdateTaskUseCase> { UpdateTaskUseCase(taskRepository = get()) }
    factory<GetAllTasksUseCase> { GetAllTasksUseCase(taskRepository = get()) }
    factory<GetFavouriteTasksUseCase> { GetFavouriteTasksUseCase(taskRepository = get()) }
    factory<GetTaskByIdUseCase> { GetTaskByIdUseCase(taskRepository = get()) }

    factory<CreateTaskGroupUseCase> { CreateTaskGroupUseCase(taskGroupRepository = get()) }
    factory<DeleteTaskGroupUseCase> { DeleteTaskGroupUseCase(taskGroupRepository = get()) }
    factory<UpdateTaskGroupUseCase> { UpdateTaskGroupUseCase(taskGroupRepository = get()) }
    factory<GetAllTaskGroupsUseCase> { GetAllTaskGroupsUseCase(taskGroupRepository = get()) }
    factory<GetTaskGroupByIdUseCase> { GetTaskGroupByIdUseCase(taskGroupRepository = get()) }
    factory<GetAllTaskItemsUseCase> { GetAllTaskItemsUseCase(taskRepository = get(), taskGroupRepository = get()) }
}