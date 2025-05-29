// File: app/src/main/java/com/keysharing/data/entity/DbNotificationEntity.kt
package com.keysharing.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keysharing.domain.NotificationType

@Entity(tableName = "notifications")
data class DbNotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val message: String,

    val type: NotificationType,

    // ID des zugehörigen Schlüssels (nullable, weil nicht jede Notification einen Schlüssel referenziert)
    val relatedKeyId: Int? = null,

    // ob die Notification bereits gelesen wurde
    val read: Boolean = false
)
