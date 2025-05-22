package com.keysharing.domain

/**
 * Domain Model: Repräsentiert einen Public Key in der Geschäftslogik.
 * Dieses Modell ist unabhängig von Persistenz und wird in ViewModels und Use-Cases verwendet.
 *
 * @property key Der öffentliche Schlüssel (z.B. Base64-codiert).
 * @property fingerprint Kurz-Fingerprint zur visuellen Verifikation (z.B. die ersten 10 Zeichen oder ein Hash-Auszug).
 */

data class PublicKey(
    val key: String,
    val fingerprint: String
)
