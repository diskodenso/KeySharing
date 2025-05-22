package com.keysharing.data

import com.keysharing.domain.Notification

/**
 * Repository-Schnittstelle für Benachrichtigungen im Notifications-Tab.
 */
interface NotificationRepository {
    /**
     * Liefert alle gespeicherten Notifications.
     */
    fun getAllNotifications(): List<Notification>

    /**
     * Markiert die Notification mit der angegebenen ID als gelesen.
     */
    fun markAsRead(id: Int)

    /**
     * Fügt eine neue Notification hinzu.
     */
    fun addNotification(notification: Notification)
}