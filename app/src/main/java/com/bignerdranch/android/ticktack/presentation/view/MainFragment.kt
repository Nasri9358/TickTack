package com.bignerdranch.android.ticktack.presentation.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.ticktack.databinding.FragmentMainBinding
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val mainFragmentViewModel by lazy { getViewModel<MainFragmentViewModel>() }
    private val adapter by lazy { TaskAdapter(OnItemClickListener(requireContext(), mainFragmentViewModel)) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTaskList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainFragment.adapter
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