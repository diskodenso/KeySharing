package com.keysharing.data.mappers

import com.keysharing.data.DbPublicKeyEntity
import com.keysharing.domain.PublicKey

/**
 * Wandelt eine Room-Entity (DbPublicKeyEntity) in das Domain Model (PublicKey) um.
 *
 * @receiver die Datenbank-Entität mit den Feldern id, name, publicKey und createdAt.
 * @return ein Domain-PublicKey-Objekt mit Schlüssel und fingerprint.
 */
fun DbPublicKeyEntity.toDomain(): PublicKey =
    PublicKey(
        key = this.publicKey,
        fingerprint = this.name // Hier verwenden wir den in der DB gespeicherten Namen als Fingerprint
    )

/**
 * Wandelt ein Domain Model (PublicKey) in eine Room-Entity (DbPublicKeyEntity) um.
 *
 * @receiver das Domain-PublicKey-Objekt mit Schlüssel und fingerprint.
 * @param displayName Name oder Fingerprint, der in der Datenbank gespeichert wird.
 * @return eine neue DbPublicKeyEntity zum Einfügen oder Aktualisieren in der DB.
 */
fun PublicKey.toDb(displayName: String): DbPublicKeyEntity =
    DbPublicKeyEntity(
        name = displayName,
        publicKey = this.key
    )