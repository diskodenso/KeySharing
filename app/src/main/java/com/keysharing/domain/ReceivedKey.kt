package com.keysharing.domain

/**
 * Domain Model für empfangene Public Keys im Dashboard.
 *
 * @property id Eindeutige ID des empfangenen Schlüssels.
 * @property name Anzeigename der Entität (z.B. "Alice").
 * @property publicKey Der Public Key als String (Base64 o.Ä.).
 * @property method Übertragungsmethode (NFC, Bluetooth, Wi-Fi Direct).
 */
data class ReceivedKey(
    val id: Int,
    val name: String,
    val publicKey: String,
    val method: TransferMethod,
    val isVerified: Boolean,
)

