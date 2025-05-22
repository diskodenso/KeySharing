package com.keysharing.data

import android.content.Context
import androidx.room.Room

/**
 * Interface für den Zugriff auf den eigenen Public Key.
 */
interface KeyRepository {
    /**
     * Liest den aktuell gespeicherten eigenen Public Key (oder null).
     */
    fun getOwnKey(): PublicKeyEntity?

    /**
     * Speichert oder aktualisiert den eigenen Public Key.
     */
    fun saveOwnKey(key: PublicKeyEntity)
}

/**
 * Room-basierte Implementierung von [KeyRepository].
 * Speichert und lädt den eigenen Public Key aus einer SQLite-Datenbank.
 */
class RoomKeyRepository(context: Context) : KeyRepository {

    // Instanziiere die Room-Datenbank
    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "keysharing.db"
    ).build()

    // DAO für PublicKey-Operationen
    private val publicKeyDao = db.publicKeyDao()

    /**
     * Liest den eigenen Public Key. Gibt null zurück, wenn noch keiner gespeichert ist.
     */
    override fun getOwnKey(): PublicKeyEntity? {
        val entity = publicKeyDao.getOwnKey() ?: return null
        // Erzeuge einen kurzen Fingerprint aus dem gespeicherten Schlüssel
        val fingerprint = entity.publicKey.take(10)
        return PublicKeyEntity(
            key = entity.publicKey,
            fingerprint = fingerprint
        )
    }

    /**
     * Speichert oder aktualisiert den eigenen Public Key.
     * Nutzt den Fingerprint als Anzeigenamen ("name").
     */
    override fun saveOwnKey(key: PublicKeyEntity) {
        val dbEntity = DbPublicKeyEntity(
            name = key.fingerprint,
            publicKey = key.key
        )
        publicKeyDao.insert(dbEntity)
    }
}
