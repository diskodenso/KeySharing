// File: app/src/main/java/com/keysharing/bluetooth/BluetoothService.kt
package com.keysharing.bluetooth

import com.keysharing.domain.PublicKey
import com.keysharing.TransferException

/** Interface für Bluetooth-basiertes Key-Sharing */
interface BluetoothService {
    @Throws(TransferException::class)
    fun sendKey(key: PublicKey)
}
