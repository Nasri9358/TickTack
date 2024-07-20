package com.bignerdranch.android.ticktack.presentation.view.taskView

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.utils.DateUtils
import com.bignerdranch.android.ticktack.presentation.adapter.TASK_NAME_EXTRA
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskActivityViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.NotificationViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    private lateinit var notificationViewModel: NotificationViewModel
    private lateinit var task: Task

    private lateinit var taskActivityViewModel: TaskActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)

        taskActivityViewModel = getViewModel()
        notificationViewModel = NotificationViewModel(this)

        task = intent.extras?.getSerializable(TASK_NAME_EXTRA) as Task

        binding.btnDeleteTask.setOnClickListener {
            cancelTaskNotification()
            deleteTask()
        }

        binding.tvTaskCompletionDate.setOnClickListener {
            clearEditTextFocus()
            setTaskCompletionDate()
        }

        binding.tvTaskGroupInfo.setOnClickListener {
            setTaskGroup()
        }

        updateTaskUI()
        setContentView(binding.root)
    }

    override fun onPause() {
        super.onPause()
        clearEditTextFocus()
        updateTask(task)
    }

    private fun updateTaskUI() {
        binding.tvTaskTitle.setText(task.title)
        binding.tvTaskDescreption.setText(task.description)

        if (task.comletionDateInMills != null) {
            binding.tvTaskComplectionDate.text = DateUtils.normalDateFormat(task.comletionDateInMills!!)
            binding.tvTaskCompletionDate.alpha = 1.0F
        } else {
            binding.tvTaskCompletionDate.alpha = 0.3F
            binding.tvTaskCompletionDate.text = "Добавить дату/ время"
        }

        if (task.taskGroupId != null ) lifecycleScope.launch {
            val taskGroup = taskActivityViewModel.getTaskGroupById(task.taskGroupId!!).await()

            withContext(Dispatchers.Main) {
                binding.tvTaskGroupInfo.alpha = 1.0F
                binding.tvTaskGroupInfo.text = taskGroup.name
            }
        } else {
            binding.tvTaskGroupInfo.alpha = 0.3F
            binding.tvTaskGroupInfo.text = "Добавить группу"
        }
    }
}