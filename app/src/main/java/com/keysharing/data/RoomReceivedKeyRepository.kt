package com.keysharing.data

import android.content.Context
import androidx.room.Room
import com.keysharing.domain.ReceivedKey
import com.keysharing.data.mappers.toDomain as dbReceivedToDomain
import com.keysharing.data.mappers.toDb as receivedToDb

/**
 * Room-basierte Implementierung von [ReceivedKeyRepository].
 * Verwaltet alle empfangenen Public Keys für das Dashboard.
 */
class RoomReceivedKeyRepository(context: Context) : ReceivedKeyRepository {
    // Baue die Room-Datenbank
    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "keysharing.db"
    ).build()
    private val dao = db.receivedKeyDao()

    override fun getAllReceivedKeys(): List<ReceivedKey> {
        // Entity-Liste aus DB holen und in Domain-Modelle mappen
        return dao.getAll().map { it.toDomain() }
    }

    override fun addReceivedKey(key: ReceivedKey) {
        // Domain-Objekt in Entity umwandeln und speichern
        val entity = key.toDb()
        dao.insert(entity)
    }

    override fun verifyReceivedKey(id: Int) {
        // Entity holen, Status ändern und updaten
        val entity = dao.getById(id) ?: return
        val updated = entity.copy(verified = true)
        dao.insert(updated)
    }
}
