package com.keysharing.domain

/**
 * Enumeration der Notification-Typen.
 */
enum class NotificationType {
    KEY_REQUEST,    // Jemand hat den eigenen Key angefragt
    KEY_RECEIVED,   // Du hast einen Key erhalten
    KEY_VERIFIED    // Ein empfangener Key wurde verifiziert
}
