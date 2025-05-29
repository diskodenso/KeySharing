// File: app/src/main/java/com/keysharing/hce/HexUtil.kt
package com.keysharing.hce

/**
 * Helfer für Hex-String ↔ ByteArray.
 */
object HexUtil {
    fun hexStringToByteArray(s: String): ByteArray =
        s.chunked(2)
            .map { it.toInt(16).toByte() }
            .toByteArray()

    fun ByteArray.toHexString(): String =
        joinToString("") { "%02X".format(it) }
}
