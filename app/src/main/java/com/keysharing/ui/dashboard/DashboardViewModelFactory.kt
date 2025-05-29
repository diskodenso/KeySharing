// File: app/src/main/java/com/keysharing/ui/dashboard/DashboardViewModelFactory.kt
package com.keysharing.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keysharing.data.repository.ReceivedKeyRepository

class DashboardViewModelFactory(
    private val repo: ReceivedKeyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
