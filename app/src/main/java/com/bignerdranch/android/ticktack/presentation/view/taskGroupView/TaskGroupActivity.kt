package com.bignerdranch.android.ticktack.presentation.view.taskGroupView

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.ticktack.databinding.ActivityTaskGroupBinding
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.presentation.adapter.OnItemClickListener
import com.bignerdranch.android.ticktack.presentation.adapter.TASK_GROUP_NAME_EXTRA
import com.bignerdranch.android.ticktack.presentation.adapter.TaskAdapter
import com.bignerdranch.android.ticktack.presentation.view.taskView.CreateTaskActivity
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskGroupActivityViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TaskGroupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskGroupBinding
    private lateinit var recycler: RecyclerView
    private lateinit var taskGroup: TaskGroup

//    private lateinit var taskGroupViewModel: TaskGroupViewModel
//    private lateinit var tasksViewModel: TasksViewModel

    private lateinit var taskGroupActivityViewModel: TaskGroupActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskGroupBinding.inflate(layoutInflater)
        taskGroupActivityViewModel = getViewModel()

        taskGroup = intent.extras?.getSerializable(TASK_GROUP_NAME_EXTRA) as TaskGroup
        recycler = binding.rvTaskGroupTasks

        val adapter = TaskAdapter(OnItemClickListener(this, taskGroupActivityViewModel))

        recycler.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        taskGroupActivityViewModel.tasks.observe(this) {
            adapter.updateList(it)

            binding.tvIsEmptyList.visibility = if (it.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        binding.btnDeleteTaskGroup.setOnClickListener {
            deleteTaskGroup()
        }

        binding.btnAddTaskToGroup.setOnClickListener {
            addTask()
        }

        setTaskGroupData()
        setContentView(binding.root)
    }

    override fun onPause() {
        super.onPause()
        clearEditTextFocus()
        updateTaskGroup(taskGroup)
    }

    override fun onResume() {
        super.onResume()
        taskGroupActivityViewModel.getAllTasksById(taskGroup.id)
    }

    private fun updateTaskGroup(newTaskGroup: TaskGroup) {
        val name = binding.tvTaskGroupName.text.toString()
        val desc = binding.tvTaskGroupDescription.text.toString()

        taskGroup = if (newTaskGroup.name != title || newTaskGroup.description != desc){
            newTaskGroup.copy(name = name, description = desc)
        } else {
            newTaskGroup
        }

        taskGroupActivityViewModel.updateTaskGroup(taskGroup)
        setTaskGroupData()
    }

    private fun addTask() {
        val intent = Intent(this, CreateTaskActivity::class.java)
        intent.putExtra("taskGroupId", taskGroup.id)
        startActivity(intent)
    }

    private fun setTaskGroupData() {
        binding.tvTaskGroupName.setText(taskGroup.name)
        binding.tvTaskGroupDescription.setText(taskGroup.description)
    }

    // добавить уведомление, что делать с задачами
    private fun deleteTaskGroup() {
        taskGroupActivityViewModel.deleteTaskGroup(taskGroup)
        finish()
    }

    private fun clearEditTextFocus() {
        binding.tvTaskGroupName.clearFocus()
        binding.tvTaskGroupDescription.clearFocus()
    }
}