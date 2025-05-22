package com.keysharing.data.mappers

import com.keysharing.data.DbReceivedKeyEntity
import com.keysharing.domain.ReceivedKey
import com.keysharing.domain.TransferMethod

/**
 * Mappen von DbReceivedKeyEntity zu Domain-Modell.
 */
fun DbReceivedKeyEntity.toDomain(): ReceivedKey =
    ReceivedKey(
        id = this.id,
        name = this.name,
        publicKey = this.publicKey,
        method = TransferMethod.valueOf(this.method),
        verified = this.verified
    )

/**
 * Mappen von Domain-Modell zu DbReceivedKeyEntity.
 */
fun ReceivedKey.toDb(): DbReceivedKeyEntity =
    DbReceivedKeyEntity(
        id = this.id,
        name = this.name,
        publicKey = this.publicKey,
        method = this.method.name,
        verified = this.verified
    )