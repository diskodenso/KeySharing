// File: app/src/main/java/com/keysharing/ui/dashboard/DashboardViewModel.kt
package com.keysharing.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keysharing.data.repository.ReceivedKeyRepository
import com.keysharing.domain.ReceivedKey

class DashboardViewModel(
    private val repository: ReceivedKeyRepository
) : ViewModel() {

    private val _receivedKeys = MutableLiveData<List<ReceivedKey>>().also {
        it.value = repository.getAllReceivedKeys()
    }
    val receivedKeys: LiveData<List<ReceivedKey>> = _receivedKeys

    fun addKey(key: ReceivedKey) {
        repository.addReceivedKey(key)
        _receivedKeys.value = repository.getAllReceivedKeys()
    }

    fun verifyKey(id: Int) {
        repository.verifyReceivedKey(id)
        _receivedKeys.value = repository.getAllReceivedKeys()
    }
}
