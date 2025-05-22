package com.keysharing.data

import com.keysharing.domain.ReceivedKey

/**
 * Repository-Schnittstelle für empfangene Public Keys im Dashboard.
 */
interface ReceivedKeyRepository {
    /**
     * Liefert alle gespeicherten empfangenen Keys.
     */
    fun getAllReceivedKeys(): List<ReceivedKey>

    /**
     * Fügt einen neuen empfangenen Key hinzu.
     */
    fun addReceivedKey(key: ReceivedKey)

    /**
     * Markiert den Key mit der angegebenen ID als verifiziert.
     */
    fun verifyReceivedKey(id: Int)