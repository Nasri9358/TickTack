package com.bignerdranch.android.ticktack.domain.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bignerdranch.android.ticktack.presentation.view.MainActivity
import com.bignerdranch.android.ticktack.R

class NotificationReceiver: BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent?) {
        val title = intent?.getStringExtra(Notification.TITLE_EXTRA_NAME) ?: return
        val message = intent.getStringExtra(Notification.MESSAGE_EXTRA_NAME) ?: return

        notificationManager = context.getSystemService(NotificationManager::class.java)
        createNotificationChannel()
        showNotification(title,message,context)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("TASK_REMINDER", "taskReminder", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(title: String, message: String, context: Context) {
        val notification = NotificationCompat.Builder(context, "TASK_REMINDER")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_tasks)
            .setColor(ContextCompat.getColor(context, R.color.accentColor))
            .setContentIntent(PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE))
            .build()

        notificationManager.notify(notification.hashCode(), notification)
    }
}