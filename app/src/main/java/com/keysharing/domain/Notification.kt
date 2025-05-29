// File: app/src/main/java/com/keysharing/data/entity/DbNotificationEntity.kt
package com.keysharing.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Notification(
    val id: Int = 0,
    val message: String,
    // z. B. REQUEST, RECEIVED, VERIFIED
    val type: NotificationType,
    // Zu welchem Key bezieht sich die Notification?
    val relatedKeyId: Int?,
    // schon gesehen?
    val read: Boolean = false
)
