package com.keysharing.data

import com.keysharing.domain.PublicKey
import java.security.PublicKey

/**
 * Interface für den Zugriff auf den eigenen Public Key.
 */
interface KeyRepository {
    /** Liest den gespeicherten eigenen Public Key oder null. */
    fun getOwnKey(): PublicKey?

    /** Speichert oder aktualisiert den eigenen Public Key. */
    fun saveOwnKey(key: PublicKey)
}