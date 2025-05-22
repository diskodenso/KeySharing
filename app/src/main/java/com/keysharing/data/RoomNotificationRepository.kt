package com.keysharing.data

import android.app.Notification
import android.content.Context
import androidx.room.Room
import com.keysharing.domain.Notification
import com.keysharing.data.mappers.notifyToDomain
import com.keysharing.data.mappers.notifyToDb

/**
 * Room-basierte Implementierung von [NotificationRepository].
 * Verwaltet alle Notifications für den Notifications-Tab.
 */
class RoomNotificationRepository(context: Context) : NotificationRepository {
    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "keysharing.db"
    ).build()
    private val dao = db.notificationDao()

    override fun getAllNotifications(): List<Notification> {
        return dao.getAll().map { it.notifyToDomain() }
    }

    override fun markAsRead(id: Int) {
        val entity = dao.getById(id) ?: return
        val updated = entity.copy(read = true)
        dao.insert(updated)
    }

    override fun addNotification(notification: Notification) {
        val entity = notification.notifyToDb()
        dao.insert(entity)
    }
}