// File: app/src/main/java/com/keysharing/data/repository/ReceivedKeyRepository.kt
package com.keysharing.data.repository

import com.keysharing.domain.ReceivedKey

/**
 * Schnittstelle für den Zugriff auf empfangene Public Keys.
 */
interface ReceivedKeyRepository {
    /** Alle gespeicherten ReceivedKeys laden. */
    fun getAllReceivedKeys(): List<ReceivedKey>

    /** Einen neuen empfangenen Key hinzufügen. */
    fun addReceivedKey(key: ReceivedKey)

    /** Einen bestehenden Key als verifiziert markieren (per ID). */
    fun verifyReceivedKey(id: Int)
}
