package com.keysharing.data.mappers

import com.keysharing.data.DbNotificationEntity
import com.keysharing.domain.Notification
import com.keysharing.domain.NotificationType

/**
 * Mappen von DbNotificationEntity zu Domain-Modell.
 */
fun DbNotificationEntity.toDomain(): Notification =
    Notification(
        id = this.id,
        message = this.message,
        type = NotificationType.valueOf(this.type),
        relatedKeyId = this.relatedKeyId,
        read = this.read
    )

/**
 * Mappen von Domain Notification zu DbNotificationEntity.
 */
fun Notification.toDb(): DbNotificationEntity =
    DbNotificationEntity(
        id = this.id,
        message = this.message,
        type = this.type.name,
        relatedKeyId = this.relatedKeyId,
        read = this.read
    )