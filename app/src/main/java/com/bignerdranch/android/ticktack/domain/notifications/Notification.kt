package com.bignerdranch.android.ticktack.domain.notifications

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.bignerdranch.android.ticktack.domain.models.Task

data class Notification(
    val id: Int,
    val title: String,
    val message: String,
    val timeInMillis: Long,
) {
    companion object {
        const val TITLE_EXTRA_NAME = "NOTIFICATION_TITLE"
        const val MESSAGE_EXTRA_NAME = "NOTIFICATION_MESSAGE"

        fun taskNotification(task: Task, timeInMills: Long): Notification {
            return Notification(
                task.title.hashCode(),
                task.title,
                "Сроки подходят, пора завершить задачу!",
                timeInMills
            )
        }
    }
}
