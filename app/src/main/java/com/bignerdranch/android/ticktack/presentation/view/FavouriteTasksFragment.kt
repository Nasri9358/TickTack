package com.bignerdranch.android.ticktack.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.ticktack.data.repository.TaskGroupRepositoryImpl
import com.bignerdranch.android.ticktack.data.room.MainDatabase
import com.bignerdranch.android.ticktack.databinding.FragmentFavouriteTasksBinding
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.GetFavouriteTasksUseCase
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.viewModel.FavouriteTasksFragmentViewModel


class FavouriteTasksFragment : Fragment() {
    private val binding by lazy { FragmentFavouriteTasksBinding.inflate(layoutInflater) }

    // Получите базу данных и DAO
    private val database by lazy { MainDatabase.getDatabase(this) }
    private val taskGroupDao by lazy { database.TaskGroupDao() }

    // Создайте репозиторий с использованием DAO
    private val repository by lazy { TaskGroupRepositoryImpl(taskGroupDao) }

    // Инициализация ViewModel с использованием конструкторной зависимости
    private val favouriteTasksFragmentViewModel by lazy {
        FavouriteTasksFragmentViewModel(
            getFavouriteTasksUseCase = GetFavouriteTasksUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository),
        )
    }

    private val recycler by lazy { binding.rvTaskList }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskAdapter(OnItemClickListener(requireContext(), favouriteTasksFragmentViewModel))

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        favouriteTasksFragmentViewModel.favouriteTasks.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }
    }

    override fun onResume() {
        super.onResume()
        favouriteTasksFragmentViewModel.getFavouriteTasks()
    }
}