package com.bignerdranch.android.ticktack.presentation.view

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.viewModel.FavouriteTasksFragmentViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var recycler: RecyclerView
    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        mainFragmentViewModel = getViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskAdapter(OnItemClickListener(requireContext(), mainFragmentViewModel))

        recycler = binding.rvTaskList
        recycler.apply {
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
}