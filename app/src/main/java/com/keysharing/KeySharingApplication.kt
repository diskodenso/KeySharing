// File: app/src/main/java/com/keysharing/KeySharingApplication.kt
package com.keysharing

import android.app.Application
import com.keysharing.data.database.AppDatabase
import com.keysharing.data.repository.RoomKeyRepository
import com.keysharing.data.repository.RoomReceivedKeyRepository
import com.keysharing.data.repository.RoomNotificationRepository

class KeySharingApplication : Application() {

    /** Singleton–Instanz der Room–Datenbank */
    val database by lazy { AppDatabase.getInstance(this) }

    /** Repository zum Verwalten des eigenen Public Keys */
    val keyRepository by lazy { RoomKeyRepository(this) }

    /** Repository zum Verwalten empfangener Public Keys */
    val receivedRepo by lazy { RoomReceivedKeyRepository(this) }

    /** Repository zum Verwalten von Notifications */
    val notificationRepo by lazy { RoomNotificationRepository(this) }
}
