package com.keysharing.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO für im Dashboard empfangene Public Keys.
 */
@Dao
interface ReceivedKeyDao {
    /**
     * Liefert alle empfangenen Public Keys.
     */
    @Query("SELECT * FROM received_keys ORDER BY id DESC")
    fun getAll(): List<DbReceivedKeyEntity>

    /**
     * Liefert einen einzelnen empfangenen Key anhand seiner ID.
     */
    @Query("SELECT * FROM received_keys WHERE id = :id")
    fun getById(id: Int): DbReceivedKeyEntity?

    /**
     * Fügt einen neuen empfangenen Key ein oder ersetzt ihn bei Konflikt.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: DbReceivedKeyEntity)

    /**
     * Entfernt einen empfangenen Key.
     */
    @Delete
    fun delete(entity: DbReceivedKeyEntity)
}