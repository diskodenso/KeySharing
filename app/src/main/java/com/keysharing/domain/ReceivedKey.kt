package com.keysharing.domain

import com.keysharing.data.TransferMethod

/**
 * Domain Model für empfangene Public Keys im Dashboard.
 *
 * @property id Eindeutige ID des empfangenen Schlüssels.
 * @property name Anzeigename der Entität (z.B. "Alice").
 * @property publicKey Der Public Key als String (Base64 o.Ä.).
 * @property method Übertragungsmethode (NFC, Bluetooth, Wi-Fi Direct).
 * @property verified Verifizierungsstatus, true wenn bestätigt.
 */
data class ReceivedKey(
    val id: Int,
    val name: String,
    val publicKey: String,
    val method: TransferMethod,
    val verified: Boolean = false
)

