package com.keysharing.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keysharing.domain.TransferMethod

/**
 * Room-Entity für einen empfangenen Public Key.
 * Speichert Name, Key-String, Übertragungsmethode und Verifizierungsstatus.
 */
@Entity(tableName = "received_keys")
data class DbReceivedKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,               // Anzeigename der Entität
    val publicKey: String,          // Public Key als String (z.B. Base64)
    val method: TransferMethod,     // NFC, BLUETOOTH oder WIFI_DIRECT
    val isVerified: Boolean = false // wurde der Key bestätigt?
)
