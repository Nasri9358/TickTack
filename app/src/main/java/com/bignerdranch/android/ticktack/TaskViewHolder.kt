package com.bignerdranch.android.ticktack

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)

    fun bind(task: Task) {
        textViewTitle.text = task.title
        textViewDescription.text = task.description
    }
}