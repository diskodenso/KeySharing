package com.keysharing.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keysharing.data.NotificationRepository
import com.keysharing.domain.Notification

/**
 * ViewModel für das Notifications-Feature.
 * Liefert eine Liste aller Notifications und ermöglicht deren Statusänderung.
 */
class NotificationsViewModel(
    private val repository: NotificationRepository
) : ViewModel() {

    // LiveData mit der Liste aller Notifications.
    private val _notifications = MutableLiveData<List<Notification>>().also {
        it.value = repository.getAllNotifications()
    }
    val notifications: LiveData<List<Notification>> = _notifications

    /**
     * Markiert eine Notification als gelesen und aktualisiert die Liste.
     */
    fun markAsRead(id: Int) {
        repository.markAsRead(id)
        _notifications.value = repository.getAllNotifications()
    }

    /**
     * Fügt eine neue Notification hinzu und aktualisiert die Liste.
     */
    fun addNotification(notification: Notification) {
        repository.addNotification(notification)
        _notifications.value = repository.getAllNotifications()
    }
}
