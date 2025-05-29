package com.keysharing.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keysharing.R
import com.keysharing.domain.Notification

/**
 * Adapter für die RecyclerView im NotificationsFragment.
 * Klick auf den „Löschen“-Button ruft onRemoveClicked auf.
 */
class NotificationAdapter(
    private val onRemoveClicked: (Int) -> Unit
) : ListAdapter<Notification, NotificationAdapter.ViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Notification>() {
            override fun areItemsTheSame(old: Notification, new: Notification) =
                old.id == new.id
            override fun areContentsTheSame(old: Notification, new: Notification) =
                old == new
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        private val btnRemove: Button  = itemView.findViewById(R.id.btnRemove)

        fun bind(item: Notification) {
            tvMessage.text = item.message
            btnRemove.setOnClickListener { onRemoveClicked(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
