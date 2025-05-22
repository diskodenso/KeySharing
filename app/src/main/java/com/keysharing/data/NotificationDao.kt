package com.keysharing.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO für Benachrichtigungen.
 */
@Dao
interface NotificationDao {
    /**
     * Liefert alle Benachrichtigungen.
     */
    @Query("SELECT * FROM notifications ORDER BY id DESC")
    fun getAll(): List<DbNotificationEntity>

    /**
     * Liefert eine Benachrichtigung anhand ihrer ID.
     */
    @Query("SELECT * FROM notifications WHERE id = :id")
    fun getById(id: Int): DbNotificationEntity?

    /**
     * Fügt eine neue Benachrichtigung ein oder ersetzt sie bei Konflikt.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: DbNotificationEntity)

    /**
     * Entfernt eine Benachrichtigung.
     */
    @Delete
    fun delete(entity: DbNotificationEntity)
}
