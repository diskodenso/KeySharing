// File: app/src/main/java/com/keysharing/data/repository/RoomKeyRepository.kt
package com.keysharing.data.repository

import android.content.Context
import com.keysharing.data.database.AppDatabase
import com.keysharing.data.entity.DbPublicKeyEntity
import com.keysharing.domain.PublicKey

/**
 * Room-basierte Implementierung von KeyRepository.
 */
class RoomKeyRepository(context: Context) : KeyRepository {

    // Hol dir das Singleton-Objekt der AppDatabase
    private val db = AppDatabase.getInstance(context)

    // Hier kommt publicKeyDao() her – stelle sicher, dass AppDatabase.publicKeyDao() existiert!
    private val publicKeyDao = db.publicKeyDao()

    override fun getOwnKey(): PublicKey? {
        val entity = publicKeyDao.getOwnKeyEntity() ?: return null
        val fingerprint = entity.publicKey.take(10)
        return PublicKey(key = entity.publicKey, fingerprint = fingerprint)
    }

    override fun saveOwnKey(key: PublicKey) {
        val entity = DbPublicKeyEntity(
            id        = 1,
            publicKey = key.key
        )
        publicKeyDao.insertOrUpdate(entity)
    }
}
