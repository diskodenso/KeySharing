// File: app/src/main/java/com/keysharing/data/repository/NotificationRepository.kt
package com.keysharing.data.repository

import com.keysharing.domain.Notification

/**
 * Schnittstelle für den Zugriff auf Notifications.
 */
interface NotificationRepository {

    /** Liefert alle gespeicherten Notifications. */
    fun getAllNotifications(): List<Notification>

    /** Fügt eine neue Notification hinzu. */
    fun addNotification(notification: Notification)

    /** Entfernt eine Notification anhand ihrer ID. */
    fun removeNotification(id: Int)
}
