// File: app/src/main/java/com/keysharing/data/repository/KeyRepository.kt
package com.keysharing.data.repository

import com.keysharing.domain.PublicKey

/**
 * Schnittstelle zum Speichern und Laden des eigenen Public Keys.
 */
interface KeyRepository {
    /** Liest den gespeicherten eigenen Public Key oder null, wenn keiner existiert. */
    fun getOwnKey(): PublicKey?

    /** Speichert oder aktualisiert den eigenen Public Key. */
    fun saveOwnKey(key: PublicKey)
}
