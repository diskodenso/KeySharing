package com.keysharing.domain

import com.keysharing.domain.NotificationType

/**
 * Domain Model für Notifications im Notification-Tab.
 *
 * @property id Eindeutige ID der Notification.
 * @property message Der sichtbare Text der Benachrichtigung.
 * @property type Typ der Benachrichtigung (Request, Received, Verified).
 * @property relatedKeyId ID des zugehörigen Schlüssels (falls vorhanden).
 * @property read true, wenn der Nutzer die Notification bereits gesehen hat.
 */
data class Notification(
    val id: Int,
    val message: String,
    val type: NotificationType,
    val relatedKeyId: Int?,
    val read: Boolean = false
)