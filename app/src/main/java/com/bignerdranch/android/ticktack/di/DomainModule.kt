package com.bignerdranch.android.ticktack.di

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