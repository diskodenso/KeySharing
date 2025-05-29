// File: app/src/main/java/com/keysharing/bluetooth/BluetoothServiceHandler.kt
package com.keysharing.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.util.Log
import com.keysharing.domain.PublicKey
import com.keysharing.TransferException

/**
 * Stub-Implementierung für Bluetooth-Übertragung.
 * Nutzt BluetoothManager, um den Adapter zu holen (nicht deprecated).
 */
class BluetoothServiceHandler(private val context: Context) {

    // statt getDefaultAdapter() über BluetoothManager
    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
        manager?.adapter
    }

    /**
     * Sendet den Public Key per Bluetooth (Stub).
     */
    fun sendKey(key: PublicKey) {
        val adapter = bluetoothAdapter ?: throw TransferException("Bluetooth nicht verfügbar")
        if (!adapter.isEnabled) {
            throw TransferException("Bluetooth ist deaktiviert")
        }

        // Hier würdest du z.B. gepairte Geräte durchgehen, Socket aufmachen usw.
        Log.d("BluetoothService", "Stub: Schlüssel ready for Bluetooth transfer: ${key.fingerprint}")
    }
}
