// File: app/src/main/java/com/keysharing/ui/dashboard/DashboardFragment.kt
package com.keysharing.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.keysharing.KeySharingApplication
import com.keysharing.R
import com.keysharing.domain.ReceivedKey
import com.keysharing.domain.TransferMethod

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    // Wichtig: explizit den VM-Typ angeben und die Factory importieren
    private val viewModel: DashboardViewModel by viewModels<DashboardViewModel> {
        DashboardViewModelFactory(
            (requireActivity().application as KeySharingApplication).receivedRepo
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView initialisieren
        val rv = view.findViewById<RecyclerView>(R.id.rvReceivedKeys).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ReceivedKeyAdapter { id: Int ->
                viewModel.verifyKey(id)
            }
        }

        // Observer mit eindeutigem Typ
        viewModel.receivedKeys.observe(viewLifecycleOwner) { list: List<ReceivedKey> ->
            (rv.adapter as ReceivedKeyAdapter).submitList(list)
        }
    }

    /** Hilfsmethode zum Testen: fügt einen Dummy-Key hinzu */
    fun onPublicKeyReceived(keyString: String) {
        viewModel.addKey(
            ReceivedKey(
                id          = 0,
                name        = keyString.take(10),
                publicKey   = keyString,
                method      = TransferMethod.NFC,
                isVerified  = true
            )
        )
    }
}
