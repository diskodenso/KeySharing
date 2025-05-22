package com.keysharing.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keysharing.data.ReceivedKeyRepository
import com.keysharing.domain.ReceivedKey

/**
 * ViewModel für das Dashboard-Feature.
 * Liefert eine Liste aller empfangenen Public Keys und ermöglicht deren Verifikation.
 */
class DashboardViewModel(
    private val repository: ReceivedKeyRepository
) : ViewModel() {

    // LiveData mit der Liste der empfangenen Keys.
    private val _receivedKeys = MutableLiveData<List<ReceivedKey>>().also {
        it.value = repository.getAllReceivedKeys()
    }
    val receivedKeys: LiveData<List<ReceivedKey>> = _receivedKeys

    /**
     * Fügt einen neuen empfangenen Key hinzu und aktualisiert die Liste.
     */
    fun addKey(key: ReceivedKey) {
        repository.addReceivedKey(key)
        _receivedKeys.value = repository.getAllReceivedKeys()
    }

    /**
     * Verifiziert einen empfangenen Key und aktualisiert die Liste.
     */
    fun verifyKey(id: Int) {
        repository.verifyReceivedKey(id)
        _receivedKeys.value = repository.getAllReceivedKeys()
    }
}
