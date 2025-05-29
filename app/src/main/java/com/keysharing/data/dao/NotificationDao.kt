// File: app/src/main/java/com/keysharing/data/dao/NotificationDao.kt
package com.keysharing.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keysharing.data.entity.DbNotificationEntity

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notifications")
    fun getAllNotifications(): List<DbNotificationEntity>

    @Insert
    fun insertNotification(entity: DbNotificationEntity): Long

    @Query("DELETE FROM notifications WHERE id = :id")
    fun deleteById(id: Int): Int
}
