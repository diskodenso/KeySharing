package com.keysharing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity für im Dashboard empfangene Public Keys.
 * Jeder Eintrag repräsentiert einen Public Key, den du von Dritten erhalten hast.
 */
@Entity(tableName = "received_keys")
data class DbReceivedKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,            // Anzeigename (z.B. "Alice")
    val publicKey: String,       // Der empfangene Public Key als String
    val method: String,          // Übertragungsmethode (z.B. "NFC", "Bluetooth")
    val verified: Boolean = false // Verifizierungsstatus
)