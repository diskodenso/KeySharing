// File: app/src/main/java/com/keysharing/data/entity/DbPublicKeyEntity.kt
package com.keysharing.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "own_key")
data class DbPublicKeyEntity(
    @PrimaryKey val id: Int = 1,    // Fest auf 1, da nur ein eigener Key existiert
    val publicKey: String
)
