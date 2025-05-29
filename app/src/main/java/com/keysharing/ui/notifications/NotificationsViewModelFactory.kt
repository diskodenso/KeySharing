// File: app/src/main/java/com/keysharing/ui/notifications/NotificationsViewModelFactory.kt
package com.keysharing.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keysharing.data.repository.NotificationRepository

/**
 * Factory zum Erzeugen von [NotificationsViewModel] mit aufgesetztem Repository.
 */
class NotificationsViewModelFactory(
    private val repo: NotificationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotificationsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotificationsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
