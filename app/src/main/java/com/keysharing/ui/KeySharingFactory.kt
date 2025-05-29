// File: app/src/main/java/com/keysharing/ui/KeySharingViewModelFactory.kt
package com.keysharing.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keysharing.KeySharingApplication
import com.keysharing.ui.dashboard.DashboardViewModel
import com.keysharing.ui.notifications.NotificationsViewModel
import com.keysharing.ui.sharekey.ShareKeyViewModel

class KeySharingViewModelFactory(
    private val app: KeySharingApplication
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when {
            modelClass.isAssignableFrom(ShareKeyViewModel::class.java) ->
                // Application + KeyRepository
                ShareKeyViewModel(
                    app,                // Application für AndroidViewModel
                    app.keyRepository   // KeyRepository
                ) as T

            modelClass.isAssignableFrom(DashboardViewModel::class.java) ->
                // nur Repository
                DashboardViewModel(
                    app.receivedRepo
                ) as T

            modelClass.isAssignableFrom(NotificationsViewModel::class.java) ->
                // nur Repository
                NotificationsViewModel(
                    app.notificationRepo
                ) as T

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
