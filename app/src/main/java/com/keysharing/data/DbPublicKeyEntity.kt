package com.keysharing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room Entity: Persistenz-Klasse für die SQLite-Tabelle "public_keys".
 * Diese Klasse enthält alle nötigen Annotationen für Room.
 */
@Entity(tableName = "public_keys")
data class DbPublicKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,            // Anzeigename (z.B. "Alice")
    val publicKey: String,       // Der Schlüssel (z.B. PEM/ Base64)
    val createdAt: Long = System.currentTimeMillis() // Zeitstempel der Speicherung
)