package com.bignerdranch.android.ticktack.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.ticktack.data.repository.TaskRepositoryImpl
import com.bignerdranch.android.ticktack.data.room.MainDatabase
import com.bignerdranch.android.ticktack.databinding.FragmentTaskBinding
import com.bignerdranch.android.ticktack.domain.usecase.taskUseCases.UpdateTaskUseCase
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskFragmentViewModel

class TaskFragment : Fragment() {
    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    // Инициализация ViewModel через конструктор с необходимыми зависимостями
    private val mainFragmentViewModel: TaskFragmentViewModel by lazy {
        // Получаем базу данных
        val database = MainDatabase.getDatabase(requireContext())

        // Получаем DAO из базы данных
        val taskDao = database.TaskDao()

        // Создаем экземпляр репозитория с использованием DAO
        val taskRepository = TaskRepositoryImpl(taskDao)

        // Создание UseCase для обновления задачи
        val updateTaskUseCase = UpdateTaskUseCase(taskRepository)

        // Передача зависимостей в ViewModel
        TaskFragmentViewModel(taskRepository, updateTaskUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем RecyclerView и его адаптер
        val adapter = TaskAdapter(OnItemClickListener(requireContext(), mainFragmentViewModel))

        binding.rvTaskList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        mainFragmentViewModel.taskItems.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }
    }

    override fun onResume() {
        super.onResume()
        mainFragmentViewModel.getAllTaskItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Очищаем binding при уничтожении представления
    }
}