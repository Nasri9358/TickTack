package com.bignerdranch.android.ticktack.presentation.view.taskView

import android.app.Activity
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import android.view.inputmethod.InputBinding
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.ticktack.R
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.notifications.Notification
import com.bignerdranch.android.ticktack.domain.utils.DateUtils
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskActivityViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskGroupActivityViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.NotificationViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.TaskViewModel

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var createTaskActivityViewModel: CreateTaskActivityViewModel
    private lateinit var notificationViewModel: NotificationViewModel

    private var task = Task("","")
    private var taskGroupId: Int? = null
    private var completionDate: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)

        createTaskActivityViewModel = getViewModel()
        notificationViewModel = NotificationViewModel(this)

        taskGroupId = intent.extras?.getInt("taskGroupId")

        binding.btnAddTask.setOnClickListener {
            createTask()

            if (task.title.isBlank()) {
                binding.inputTaskTitle.error = getString(R.string.enterTitle)
                return@setOnClickListener
            }

            if (completionDate != null) notificationViewModel.setTaskNotification(task, completionDate!!)
            createTaskActivityViewModel.createTask(task)

            finish()
        }

        binding.btnSetTaskCompletionDate.setOnClickListener {
            setTaskCompletionDate()
        }

        setContentView(binding.root)
    }

    private fun createTask() {
        val title = binding.inputTaskTitle.text.toString()
        val descriptor = binding.inputTaskDescription.text.toString()
        val isFavourite = binding.cbIsFavourite.isChecked
        val completionDateInMillis = completionDate

        task = task.copy(title = title, description = description, isFavourite = isFavourite, completionDateInMillis = completionDateInMillis, taskGroupId = taskGroupId)
    }

    private fun setTaskCompletionDate() {
        val calendar = Calendar.getInstance()

        val timePicker = timePickerDialog(this, {_, hourOfDay,minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)

            completionDate = calendar.timeInMillis

            binding.btnSetTaskCompletionDate.text = DateUtils.normalDateFormat(calendar.timeInMillis)

        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(calendar.MONTH), true)

        val datePickerDialog = DatePickerDialog(this, {_, year,month, dayOnMonth ->
            calendar.set(year, month, dayOnMonth)

            timePicker.show()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        if (completionDate != null) {
            datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Удалить") {_, _ ->
                completionDate = null
                binding.btnSetTaskCompletionDate.text = "Назначить"
            }
        }

        datePickerDialog.show()
    }
}