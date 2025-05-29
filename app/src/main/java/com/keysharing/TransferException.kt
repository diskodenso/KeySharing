// File: app/src/main/java/com/keysharing/TransferException.kt
package com.keysharing

/**
 * Wird geworfen, wenn eine Übertragung (NFC, BT, Wi-Fi Direct) fehlschlägt.
 */
class TransferException(message: String) : Exception(message)
