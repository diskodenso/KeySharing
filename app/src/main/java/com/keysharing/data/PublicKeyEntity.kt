package com.keysharing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Datenklasse für die Repräsentation eines Public Keys in der Datenbank.
 * Jeder Public Key gehört zu einer Person oder Entität.
 */
@Entity(tableName = "public_keys") // Room-Annotation: Tabelle mit dem Namen "public_keys"
data class PublicKeyEntity(
    @PrimaryKey(autoGenerate = true) // Automatisch generierter Primärschlüssel
    val id: Int = 0,

    val name: String,               // Anzeigename des Schlüssels (z. B. "Alice", "HTW Mailserver")
    val publicKey: String,         // Der eigentliche Public Key als String (z. B. PEM oder Base64)
    val createdAt: Long = System.currentTimeMillis() // Zeitpunkt der Speicherung
)