package com.keysharing.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Haupt-Datenbank-Klasse für Room. Enthält alle Entity-Klassen und DAOs.
 */
@Database(
    entities = [
        DbPublicKeyEntity::class,
        DbReceivedKeyEntity::class,
        DbNotificationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    /** Zugriff auf PublicKeyDao für den eigenen Public Key */
    abstract fun publicKeyDao(): PublicKeyDao

    /** Zugriff auf ReceivedKeyDao für empfangene Keys */
    abstract fun receivedKeyDao(): ReceivedKeyDao

    /** Zugriff auf NotificationDao für Benachrichtigungen */
    abstract fun notificationDao(): NotificationDao
}
