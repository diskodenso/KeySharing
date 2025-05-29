// File: app/src/main/java/com/keysharing/ui/sharekey/ShareKeyViewModelFactory.kt
package com.keysharing.ui.sharekey

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keysharing.KeySharingApplication
import com.keysharing.data.repository.KeyRepository

class ShareKeyViewModelFactory(
    private val app: KeySharingApplication,   // Deine Application-Klasse
    private val keyRepo: KeyRepository        // Dein Repository aus der App

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShareKeyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShareKeyViewModel(
                app,          // Application
                keyRepo       // Repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
