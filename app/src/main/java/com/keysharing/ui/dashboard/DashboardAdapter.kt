package com.keysharing.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keysharing.R
import com.keysharing.domain.ReceivedKey

/**
 * Adapter für die RecyclerView im DashboardFragment.
 * Klick auf ein Item löst die Verifikation (onVerifyClicked) aus.
 */
class ReceivedKeyAdapter(
    private val onVerifyClicked: (Int) -> Unit
) : ListAdapter<ReceivedKey, ReceivedKeyAdapter.ViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ReceivedKey>() {
            override fun areItemsTheSame(old: ReceivedKey, new: ReceivedKey) =
                old.id == new.id
            override fun areContentsTheSame(old: ReceivedKey, new: ReceivedKey) =
                old == new
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView   = itemView.findViewById(R.id.tvName)
        private val tvMethod: TextView = itemView.findViewById(R.id.tvMethod)

        fun bind(item: ReceivedKey) {
            tvName.text = item.name
            tvMethod.text = "${item.method.name.replace('_', ' ')}${if (item.isVerified) " ✓" else ""}"
            itemView.setOnClickListener { onVerifyClicked(item.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_received, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
