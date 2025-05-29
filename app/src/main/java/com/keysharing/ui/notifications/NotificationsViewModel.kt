// File: app/src/main/java/com/keysharing/ui/notifications/NotificationsViewModel.kt
package com.keysharing.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keysharing.domain.Notification
import com.keysharing.data.repository.NotificationRepository

/**
 * ViewModel für das Notifications-Feature.
 * Verwaltet eine Liste von Notifications und erlaubt Hinzufügen und Löschen.
 */
class NotificationsViewModel(
    private val repository: NotificationRepository
) : ViewModel() {

    // LiveData mit der Liste aller Notifications
    private val _notifications = MutableLiveData<List<Notification>>().also {
        it.value = repository.getAllNotifications()
    }
    val notifications: LiveData<List<Notification>> = _notifications

    /**
     * Fügt eine neue Notification hinzu und aktualisiert die Liste.
     */
    fun addNotification(notification: Notification) {
        repository.addNotification(notification)
        _notifications.value = repository.getAllNotifications()
    }

    /**
     * Entfernt eine Notification anhand ihrer ID und aktualisiert die Liste.
     */
    fun removeNotification(id: Int) {
        repository.removeNotification(id)
        _notifications.value = repository.getAllNotifications()
    }
}
