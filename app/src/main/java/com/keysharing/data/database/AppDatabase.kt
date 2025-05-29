package com.keysharing.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.keysharing.data.dao.NotificationDao
import com.keysharing.data.dao.PublicKeyDao
import com.keysharing.data.dao.ReceivedKeyDao
import com.keysharing.data.entity.DbReceivedKeyEntity
import com.keysharing.data.entity.DbPublicKeyEntity
import com.keysharing.data.entity.DbNotificationEntity


/**
 * Room-Datenbank-Definition für KeySharing.
 * Enthält alle Entities und DAOs und nutzt die TypeConverters.
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
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /** DAO für empfangene Keys. */
    abstract fun receivedKeyDao(): ReceivedKeyDao
    abstract fun publicKeyDao(): PublicKeyDao
    abstract fun notificationDao(): NotificationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Liefert das Singleton der Datenbank, baut sie falls nötig neu auf.
         */
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "keysharing.db"
            ).build()
    }
}
