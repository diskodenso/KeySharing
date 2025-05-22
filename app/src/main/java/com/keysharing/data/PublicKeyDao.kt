package com.keysharing.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete


/**
 * DAO für den eigenen Public Key. -> ShareKeyFragment
 */
@Dao
interface PublicKeyDao {
    /**
     * Liest den eigenen Public Key. Da wir nur einen speichern, holen wir das erste Element.
     */
    @Query("SELECT * FROM public_keys LIMIT 1")
    fun getOwnKey(): DbPublicKeyEntity?

    /**
     * Speichert oder aktualisiert den eigenen Public Key.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(key: DbPublicKeyEntity)

    /**
     * Löscht den eigenen Public Key (oder alle, falls mehrere existieren).
     */
    @Query("DELETE FROM public_keys")
    fun deleteAll()
}