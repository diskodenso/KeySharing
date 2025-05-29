package com.keysharing.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.keysharing.data.entity.DbReceivedKeyEntity

/**
 * DAO für den Zugriff auf die Tabelle "received_keys".
 * Hier definiert man CRUD-Operationen für empfangene Keys.
 */
@Dao
interface ReceivedKeyDao {

    /** Alle empfangenen Keys auslesen. */
    @Query("SELECT * FROM received_keys")
    fun getAllReceivedKeys(): List<DbReceivedKeyEntity>

    /** Einen neuen empfangenen Key in die DB einfügen. */
    @Insert
    fun insertReceivedKey(entity: DbReceivedKeyEntity): Long

    /** Einen bestehenden empfangenen Key updaten (z.B. Verifizierungsstatus). */
    @Update
    fun updateReceivedKey(entity: DbReceivedKeyEntity): Int

    /** Einen einzelnen Key anhand seiner ID auslesen. */
    @Query("SELECT * FROM received_keys WHERE id = :id")
    fun getReceivedKeyById(id: Int): DbReceivedKeyEntity?
}
