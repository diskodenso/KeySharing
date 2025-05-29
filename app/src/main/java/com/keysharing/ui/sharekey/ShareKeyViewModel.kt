// File: app/src/main/java/com/keysharing/ui/sharekey/ShareKeyViewModel.kt
package com.keysharing.ui.sharekey

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.keysharing.data.repository.KeyRepository
import com.keysharing.domain.PublicKey
import com.keysharing.domain.TransferMethod
import com.keysharing.hce.KeyHostApduService
import com.keysharing.bluetooth.BluetoothServiceHandler
import com.keysharing.wifidirect.WifiDirectServiceHandler

class ShareKeyViewModel(
    application: Application,              // Application-Kontext via AndroidViewModel
    private val keyRepo: KeyRepository     // dein Repository
) : AndroidViewModel(application) {

    // bequemer Zugriff auf den Application-Context
    private val appContext get() = getApplication<Application>()

    // LiveData für den eigenen Schlüssel
    private val _ownKey = MutableLiveData<PublicKey?>().apply {
        value = keyRepo.getOwnKey()
    }
    val ownKey: LiveData<PublicKey?> = _ownKey

    // LiveData für Statusmeldungen
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    /** Speichert oder aktualisiert den eigenen Public Key */
    fun saveOwnKey(key: PublicKey) {
        keyRepo.saveOwnKey(key)
        _ownKey.value = key
    }

    /**
     * Teilt den eigenen Key über die gewählte Methode.
     * Für NFC wird jetzt dein HostApduService gestartet (HCE-Mode).
     */
    fun shareKey(method: TransferMethod) {
        val current = _ownKey.value
        if (current == null) {
            _status.value = "Kein Public Key hinterlegt"
            return
        }

        try {
            when (method) {
                TransferMethod.NFC -> {
                    // HCE Service starten
                    val intent = Intent(appContext, KeyHostApduService::class.java)
                    appContext.startService(intent)
                    _status.value = "HCE-Service gestartet – halte das Gerät ans Lesegerät."
                }
                TransferMethod.BLUETOOTH -> {
                    BluetoothServiceHandler(appContext).sendKey(current)
                    _status.value = "Key via Bluetooth gesendet"
                }
                TransferMethod.WIFI_DIRECT -> {
                    WifiDirectServiceHandler(appContext).sendKey(current)
                    _status.value = "Key via Wi-Fi Direct gesendet"
                }
            }
        } catch (e: Exception) {
            _status.value = "Fehler beim Senden: ${e.message}"
        }
    }
}
