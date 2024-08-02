package com.bignerdranch.android.ticktack.presentation.view


import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.ticktack.databinding.DialogCreateItemBinding
import com.bignerdranch.android.ticktack.databinding.FragmentMainBinding
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.view.taskGroupView.CreateTaskGroupActivity
import com.bignerdranch.android.ticktack.presentation.view.taskView.CreateTaskActivity
import com.bignerdranch.android.ticktack.presentation.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TaskFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val mainFragmentViewModel by lazy { getViewModel<MainFragmentViewModel>() }
    private val adapter by lazy { TaskAdapter(OnItemClickListener(requireContext(), mainFragmentViewModel)) }
    private val createItemDialog by lazy { Dialog(requireContext()) }


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
            adapter = this@TaskFragment.adapter
        }

        binding.btnCreateItem.setOnClickListener {
            showCreateItemDialog()
        }

        mainFragmentViewModel.taskItems.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
        }
    }

    override fun onResume() {
        super.onResume()
        mainFragmentViewModel.getAllTaskItems()
    }

    private fun showCreateItemDialog() {
        val dialogBinding = DialogCreateItemBinding.inflate(layoutInflater)

        createItemDialog.setContentView(dialogBinding.root)
        createItemDialog.setCancelable(true)
        createItemDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogBinding.btnCreateTask.setOnClickListener {
            startActivity(Intent(requireContext(), CreateTaskActivity::class.java))
            createItemDialog.dismiss()
        }

        dialogBinding.btnCreateTaskGroup.setOnClickListener {
            startActivity(Intent(requireContext(), CreateTaskGroupActivity::class.java))
            createItemDialog.dismiss()
        }

        createItemDialog.show()
    }
}