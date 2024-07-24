package com.bignerdranch.android.ticktack.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.ticktack.databinding.FragmentFavouriteTasksBinding
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.viewModel.FavouriteTasksFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FavouriteTasksFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteTasksBinding
    private lateinit var recycler: RecyclerView
    private lateinit var favouriteTasksFragmentViewModel: FavouriteTasksFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavouriteTasksBinding.inflate(layoutInflater, container, false)
        favouriteTasksFragmentViewModel = getViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskAdapter(OnItemClickListener(requireContext(), favouriteTasksFragmentViewModel))

        recycler = binding.rvTaskList
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