package com.keysharing.data.repository

import android.content.Context
import com.keysharing.data.dao.ReceivedKeyDao
import com.keysharing.data.database.AppDatabase
import com.keysharing.data.entity.DbReceivedKeyEntity
import com.keysharing.domain.ReceivedKey
import com.keysharing.domain.TransferMethod

/**
 * Room-basierte Implementierung von ReceivedKeyRepository.
 * Wandelt zwischen Domain-Model und Db-Entity und kapselt DAO-Aufrufe.
 */
class RoomReceivedKeyRepository(context: Context) : ReceivedKeyRepository {

    // Singleton-DB-Instanz holen
    private val dao: ReceivedKeyDao =
        AppDatabase.getInstance(context).receivedKeyDao()

    override fun getAllReceivedKeys(): List<ReceivedKey> =
        dao.getAllReceivedKeys()
            .map { entity ->
                // Entity → Domain
                ReceivedKey(
                    id         = entity.id,
                    name       = entity.name,
                    publicKey  = entity.publicKey,
                    method     = entity.method,
                    isVerified = entity.isVerified
                )
            }

    override fun addReceivedKey(key: ReceivedKey) {
        // Domain → Entity
        val entity = DbReceivedKeyEntity(
            name       = key.name,
            publicKey  = key.publicKey,
            method     = key.method,
            isVerified = key.isVerified
        )
        dao.insertReceivedKey(entity)
    }

    override fun verifyReceivedKey(id: Int) {
        // ID holen, Status ändern und updaten
        val existing = dao.getReceivedKeyById(id) ?: return
        val updated  = existing.copy(isVerified = true)
        dao.updateReceivedKey(updated)
    }
}
