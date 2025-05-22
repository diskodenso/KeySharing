// File: app/src/main/java/com/keysharing/domain/TransferMethod.kt
package com.keysharing.domain

/**
 * Enumeration der unterstützten Übertragungsmethoden für Public Keys.
 */
enum class TransferMethod {
    /**
     * Nahfeldkommunikation (NFC).
     */
    NFC,

    /**
     * Bluetooth-Verbindung.
     */
    BLUETOOTH,

    /**
     * Wi‑Fi Direct (Peer-to-Peer Wi‑Fi).
     */
    WIFI_DIRECT
}
