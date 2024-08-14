package com.bignerdranch.android.ticktack.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.ticktack.databinding.ActivityCreateTaskGroupBinding
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskFragmentViewModel

class TaskFragment : Fragment() {
    // Используем более подходящий для фрагмента binding (вместо Activity binding)
    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    // Инициализация ViewModel через делегат viewModels
    private val mainFragmentViewModel: TaskFragmentViewModel by viewModels()

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