package com.bignerdranch.android.ticktack.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.viewModel.FavouriteTasksFragmentViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.MainFragmentViewModel

class FavouriteTaskFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteTaskBinding
    private lateinit var recycler: RecyclerView
    private lateinit var favouriteTaskFragmentViewModel: FavouriteTasksFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoutiteTaskBinding.inflate(layoutInflater,container, false)
        favouriteTaskFragmentViewModel = getViewMdel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskAdapter(OnItemClickListener(requireContext(), favouriteTaskFragmentViewModel))

        recycler = binding.rvTaskList
        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        favouriteTaskFragmentViewModel.favouriteTasks.observe(viewLifecycleOwner) {list ->
            adapter.updateList(list)
        }
    }

    override fun onResume() {
        super.onResume()
        favouriteTaskFragmentViewModel.getFavouriteTasks()
    }
}