// File: app/src/main/java/com/keysharing/wifidirect/WifiDirectService.kt
package com.keysharing.wifidirect

import com.keysharing.domain.PublicKey
import com.keysharing.TransferException

/** Interface für Wi-Fi Direct–basiertes Key-Sharing */
interface WifiDirectService {
    @Throws(TransferException::class)
    fun sendKey(key: PublicKey)
}
