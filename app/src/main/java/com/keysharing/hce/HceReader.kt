// File: app/src/main/java/com/keysharing/hce/HceReader.kt
package com.keysharing.hce

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.util.Log
import com.keysharing.domain.PublicKey
import com.keysharing.TransferException

/**
 * Reader-Modus für HCE: Sendet SELECT und GET-KEY-APDUs,
 * liest den Public Key aus und liefert ihn per Callback.
 */
class HceReader(
    private val onKeyRead: (String) -> Unit
) : NfcAdapter.ReaderCallback {

    companion object {
        private const val TAG = "HceReader"

        // AID (muss mit dem Service übereinstimmen)
        private val AID = HexUtil.hexStringToByteArray("F222222222")

        // SELECT + GET-KEY
        private val SELECT_APDU = buildSelectApdu(AID)
        private val STATUS_OK   = byteArrayOf(0x90.toByte(), 0x00.toByte())
        private val GET_KEY_APDU = byteArrayOf(
            0x00, 0xCA.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte()
        )

        private fun buildSelectApdu(aid: ByteArray): ByteArray {
            val header = byteArrayOf(0x00, 0xA4.toByte(), 0x04.toByte(), 0x00.toByte())
            return header + byteArrayOf(aid.size.toByte()) + aid + byteArrayOf(0x00)
        }
    }

    override fun onTagDiscovered(tag: Tag) {
        IsoDep.get(tag)?.use { iso ->
            try {
                iso.connect()
                // SELECT
                val selectResp = iso.transceive(SELECT_APDU)
                if (!selectResp.endsWith(STATUS_OK)) return

                // GET-KEY
                val keyResp = iso.transceive(GET_KEY_APDU)
                if (keyResp.size < 2) return
                val dataBytes = keyResp.dropLast(2).toByteArray()
                val status    = keyResp.takeLast(2).toByteArray()
                if (!status.contentEquals(STATUS_OK)) return

                val keyString = String(dataBytes, Charsets.UTF_8)
                onKeyRead(keyString)

            } catch (e: Exception) {
                Log.e(TAG, "HCE-Read-Error", e)
            }
        } ?: Log.e(TAG, "IsoDep nicht unterstützt")
    }

    // Hilfs-Erweiterung
    private fun ByteArray.endsWith(suffix: ByteArray) =
        this.takeLast(suffix.size).toByteArray().contentEquals(suffix)
}
