package com.bignerdranch.android.ticktack.presentation.viewModel

import android.content.Context
import com.bignerdranch.android.ticktack.domain.models.Task
import com.bignerdranch.android.ticktack.domain.notifications.Notification
import com.bignerdranch.android.ticktack.domain.notifications.NotificationScheduler

class NotificationViewModel(context: Context) {
    private val notificationScheduler = NotificationScheduler(context)

    fun setTaskNotification(task: Task, timeInMillis: Long) {
        val notification = Notification.taskNotification(task,timeInMillis)
        notificationScheduler.schedule(notification)
    }

    fun cancelTaskNotification(task: Task, timeInMillis: Long) {
        val notification = Notification.taskNotification(task,timeInMillis)
        notificationScheduler.cancel(notification)
    }
}