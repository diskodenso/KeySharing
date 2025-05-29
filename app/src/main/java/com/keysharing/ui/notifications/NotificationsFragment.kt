// File: app/src/main/java/com/keysharing/ui/notifications/NotificationsFragment.kt
package com.keysharing.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keysharing.KeySharingApplication
import com.keysharing.R
import com.keysharing.domain.Notification

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    // explizit den VM-Typ angeben und die Factory importieren
    private val viewModel: NotificationsViewModel by viewModels<NotificationsViewModel> {
        NotificationsViewModelFactory(
            (requireActivity().application as KeySharingApplication).notificationRepo
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView initialisieren
        val rv = view.findViewById<RecyclerView>(R.id.rvNotifications).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = NotificationAdapter { id: Int ->
                viewModel.removeNotification(id)
            }
        }

        // Observer mit eindeutigem Typ
        viewModel.notifications.observe(viewLifecycleOwner) { list: List<Notification> ->
            (rv.adapter as NotificationAdapter).submitList(list)
        }
    }
}
