package com.keysharing.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Delete

/**
 * Data Access Object (DAO) für den Zugriff auf die Public Keys in der Datenbank.
 * Definiert die möglichen Datenbankoperationen (CRUD).
 */
@Dao
interface KeyDao {

    /**
     * Liefert alle gespeicherten Public Keys als LiveData-Liste.
     * LiveData ermöglicht automatische UI-Aktualisierung bei Änderungen.
     */
    @Query("SELECT * FROM public_keys")
    fun getAll(): LiveData<List<PublicKeyEntity>>

    /**
     * Fügt einen neuen Public Key ein oder ersetzt einen vorhandenen mit gleichem Primärschlüssel.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(key: PublicKeyEntity)

    /**
     * Löscht den angegebenen Public Key aus der Datenbank.
     */
    @Delete
    suspend fun delete(key: PublicKeyEntity)
}