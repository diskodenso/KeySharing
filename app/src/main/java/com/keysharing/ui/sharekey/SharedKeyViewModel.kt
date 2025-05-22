package com.keysharing.ui.sharekey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keysharing.data.KeyRepository
import com.keysharing.domain.PublicKey
import com.keysharing.domain.TransferMethod

/**
 * ViewModel für das ShareKey-Feature.
 * Verwaltet den eigenen Public Key und löst das Senden über verschiedene Methoden aus.
 */
class ShareKeyViewModel(
    private val repository: KeyRepository
) : ViewModel() {

    // LiveData für den aktuell gespeicherten eigenen Public Key.
    private val _ownKey = MutableLiveData<PublicKey?>().also {
        it.value = repository.getOwnKey()
    }
    val ownKey: LiveData<PublicKey?> = _ownKey

    // LiveData für Statusnachrichten (z.B. Erfolg, Fehler beim Senden)
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    /**
     * Speichert oder aktualisiert den eigenen Public Key und aktualisiert LiveData.
     */
    fun saveKey(key: PublicKey) {
        repository.saveOwnKey(key)
        _ownKey.value = key
        _status.value = "Key gespeichert"
    }

    /**
     * Sendet den eigenen Public Key über die angegebene Methode.
     */
    fun shareKey(method: TransferMethod) {
        val key = _ownKey.value
        if (key == null) {
            _status.value = "Kein Public Key zum Teilen vorhanden"
            return
        }
        try {
            repository.sendOwnKey(method)  // falls repository implementiert sendOwnKey
            _status.value = "Key über ${method.name} gesendet"
        } catch (e: Exception) {
            _status.value = "Fehler beim Senden: ${e.message}"
        }
    }
}

