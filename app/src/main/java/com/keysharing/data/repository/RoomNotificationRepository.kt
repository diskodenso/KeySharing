// File: app/src/main/java/com/keysharing/data/repository/RoomNotificationRepository.kt
package com.keysharing.data.repository

import android.content.Context
import com.keysharing.data.dao.NotificationDao
import com.keysharing.data.database.AppDatabase
import com.keysharing.data.entity.DbNotificationEntity
import com.keysharing.domain.Notification

class RoomNotificationRepository(context: Context) : NotificationRepository {

    private val dao: NotificationDao =
        AppDatabase.getInstance(context).notificationDao()

    override fun getAllNotifications(): List<Notification> =
        dao.getAllNotifications().map { entity ->
            Notification(
                entity.id,
                entity.message,
                entity.type,
                entity.relatedKeyId,
                entity.read
            )
        }

    override fun addNotification(notification: Notification) {
        val entity = DbNotificationEntity(
            message      = notification.message,
            type         = notification.type,
            relatedKeyId = notification.relatedKeyId,
            read         = notification.read
        )
        // Positional argument hier
        dao.insertNotification(entity)
    }

    override fun removeNotification(id: Int) {
        // Auch hier rein positionsbasiert
        dao.deleteById(id)
    }
}
