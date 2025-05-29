// File: app/src/main/java/com/keysharing/wifidirect/WifiDirectServiceHandler.kt
package com.keysharing.wifidirect

import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import android.util.Log
import com.keysharing.TransferException
import com.keysharing.domain.PublicKey

/**
 * Stub-Implementierung: hier würdest du Peers entdecken und P2P-Sockets nutzen.
 */
class WifiDirectServiceHandler(private val context: Context) : WifiDirectService {

    private val manager = context.getSystemService(Context.WIFI_P2P_SERVICE) as? WifiP2pManager
    private val channel = manager?.initialize(context, context.mainLooper, null)

    override fun sendKey(key: PublicKey) {
        val mgr = manager ?: throw TransferException("Wi-Fi Direct nicht verfügbar")
        val ch  = channel ?: throw TransferException("Kanäle nicht initialisiert")

        mgr.discoverPeers(ch, object : WifiP2pManager.ActionListener {
            override fun onSuccess() { Log.d("WifiDirect", "Peers entdecken gestartet") }
            override fun onFailure(reason: Int) {
                throw TransferException("Peer-Erkennung fehlgeschlagen: $reason")
            }
        })
        Log.d("WifiDirectService", "Stub: Key bereit zur P2P-Übertragung")
    }
}
