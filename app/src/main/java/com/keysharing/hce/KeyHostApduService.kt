// Datei: app/src/main/java/com/keysharing/hce/KeyHostApduService.kt

@file:OptIn(ExperimentalStdlibApi::class)

package com.keysharing.hce

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import com.keysharing.TransferException
import com.keysharing.data.repository.RoomKeyRepository
import com.keysharing.domain.PublicKey
import kotlin.ExperimentalStdlibApi

/**
 * Host-based Card Emulation Service – liefert auf SELECT und GET-KEY-APDUs deinen Public Key.
 */
class KeyHostApduService : HostApduService() {

    companion object {
        private const val TAG = "KeyHostApduService"

        // AID (hier als Hex-String)
        private val AID = HexUtil.hexStringToByteArray("F222222222")

        // ISO-7816-4 Status-Bytes
        private val STATUS_SUCCESS = byteArrayOf(0x90.toByte(), 0x00.toByte())
        private val STATUS_FAILED  = byteArrayOf(0x6F.toByte(), 0x00.toByte())

        // Baut ein SELECT-APDU: CLA INS P1 P2 Lc AID Le
        private fun buildSelectApdu(aid: ByteArray): ByteArray {
            val header = byteArrayOf(0x00, 0xA4.toByte(), 0x04.toByte(), 0x00.toByte())
            return header + byteArrayOf(aid.size.toByte()) + aid + byteArrayOf(0x00)
        }

        // Vorgefertigte SELECT_APDU für den schnellen Vergleich
        private val SELECT_APDU = buildSelectApdu(AID)

        // GET-KEY-APDU-Header (CLA=0x00, INS=0xCA, P1=P2=0x00, Le=0x00)
        private val GET_KEY_APDU_HEADER =
            byteArrayOf(0x00, 0xCA.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte())
    }

    override fun processCommandApdu(commandApdu: ByteArray, extras: Bundle?): ByteArray {
        Log.d(TAG, "APDU empfangen: ${commandApdu.toHexString()}")

        return when {
            // 1) AID-Selektion
            commandApdu.contentEquals(SELECT_APDU) -> {
                Log.d(TAG, "SELECT AID erkannt")
                STATUS_SUCCESS
            }

            // 2) GET-KEY-APDU erkennen
            commandApdu.size >= GET_KEY_APDU_HEADER.size &&
                    commandApdu.copyOfRange(0, GET_KEY_APDU_HEADER.size)
                        .contentEquals(GET_KEY_APDU_HEADER) -> {
                Log.d(TAG, "GET-KEY-APDU erkannt")
                val repo = RoomKeyRepository(applicationContext)
                val keyString = repo.getOwnKey()?.key ?: return STATUS_FAILED
                keyString.toByteArray(Charsets.UTF_8) + STATUS_SUCCESS
            }

            else -> {
                Log.w(TAG, "Unbekannte APDU: ${commandApdu.toHexString()}")
                STATUS_FAILED
            }
        }
    }

    override fun onDeactivated(reason: Int) {
        Log.d(TAG, "HCE-Service deaktiviert (reason=$reason)")
    }
}
