package com.bignerdranch.android.ticktack.presentation.view.taskView

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.ticktack.R
import com.bignerdranch.android.ticktack.data.repository.TaskRepositoryImpl
import com.bignerdranch.android.ticktack.data.room.MainDatabase
import com.bignerdranch.android.ticktack.databinding.ActivityCreateTaskBinding
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.utils.DateUtils
import com.bignerdranch.android.ticktack.presentation.viewModel.CreateTaskActivityViewModel
import com.bignerdranch.android.ticktack.presentation.viewModel.NotificationViewModel

class CreateTaskActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCreateTaskBinding.inflate(layoutInflater) }

    // Инициализация базы данных и DAO
    private val database by lazy { MainDatabase.getDatabase(this) }
    private val taskDao by lazy { database.TaskDao() }
    private val repository by lazy { TaskRepositoryImpl(taskDao) }

    // Создание ViewModel и передача зависимости через конструктор
    private val createTaskActivityViewModel: CreateTaskActivityViewModel by lazy {
        CreateTaskActivityViewModel(repository)
    }

    private val notificationViewModel by lazy { NotificationViewModel(this) }

    private var task = Task("", "")
    private var taskGroupId: Int? = null
    private var completionDate: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        taskGroupId = intent.extras?.getInt("taskGroupId")

        binding.btnAddTask.setOnClickListener {
            createTask()

            if (task.title.isBlank()) {
                binding.inputTaskTitle.error = getString(R.string.enterTitle)
                return@setOnClickListener
            }

            if (completionDate != null) {
                notificationViewModel.setTaskNotification(task, completionDate!!)
            }
            createTaskActivityViewModel.createTask(task)
            finish()
        }

        binding.btnSetTaskCompletionDate.setOnClickListener {
            setTaskCompletionDate()
        }
    }

    private fun createTask() {
        val title = binding.inputTaskTitle.text.toString()
        val description = binding.inputTaskDescription.text.toString()
        val isFavourite = binding.cbIsFavourive.isChecked
        val completionDateInMillis = completionDate

        task = task.copy(
            title = title,
            description = description,
            isFavourite = isFavourite,
            completionDateInMillis = completionDateInMillis,
            taskGroupId = taskGroupId
        )
    }

    private fun setTaskCompletionDate() {
        val calendar = Calendar.getInstance()

        val timePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)

            completionDate = calendar.timeInMillis
            binding.btnSetTaskCompletionDate.text = DateUtils.normalDateFormat(calendar.timeInMillis)
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            timePicker.show()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        if (completionDate != null) {
            datePickerDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Удалить") { _, _ ->
                completionDate = null
                binding.btnSetTaskCompletionDate.text = "Назначить"
            }
        }

        datePickerDialog.show()
    }
}