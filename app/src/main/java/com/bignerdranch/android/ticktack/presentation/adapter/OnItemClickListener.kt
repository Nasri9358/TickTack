package com.bignerdranch.android.ticktack.presentation.adapter

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.models.TaskGroup
import com.bignerdranch.android.ticktack.domain.models.TaskItem
import com.bignerdranch.android.ticktack.presentation.view.taskGroupView.TaskGroupActivity
import com.bignerdranch.android.ticktack.presentation.view.taskView.TaskActivity
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskViewModel

const val TASK_NAME_EXTRA = "task"
const val TASK_GROUP_NAME_EXTRA = "taskGroup"

class OnItemClickListener(private val context: Context, private val viewModel: TaskViewModel): ItemClickListener {
    override fun onItemCLicked(taskItem: TaskItem) {
        val intent: Intent = when(taskItem) {
            is Task -> {
                Intent(context, TaskActivity::class.java).apply {
                    putExtra(TASK_NAME_EXTRA, taskItem)
                }
            }

            is TaskGroup -> {
                Intent(context, TaskGroupActivity::class.java).apply {
                    putExtra(TASK_GROUP_NAME_EXTRA, taskItem)
                }
            }
        }

        context.startActivity(intent)
    }

    override fun onTaskCheckboxClicked(task: Task) {
        viewModel.completeTask(task)
    }
}