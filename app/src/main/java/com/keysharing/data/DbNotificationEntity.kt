package com.keysharing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity für Benachrichtigungen.
 * Speichert alle Notifications, die im Notifications-Tab angezeigt werden.
 */
@Entity(tableName = "notifications")
data class DbNotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val message: String,         // Textinhalt der Notification
    val type: String,            // Typ, z.B. "KEY_REQUEST", "KEY_RECEIVED", "KEY_VERIFIED"
    val relatedKeyId: Int?,      // Verknüpfung zu einem ReceivedKeyEntity (falls vorhanden)
    val read: Boolean = false    // Gelesen-Flag
)