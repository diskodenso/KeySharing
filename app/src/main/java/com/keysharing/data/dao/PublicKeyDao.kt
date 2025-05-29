// File: app/src/main/java/com/keysharing/data/dao/PublicKeyDao.kt
package com.keysharing.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keysharing.data.entity.DbPublicKeyEntity

@Dao
interface PublicKeyDao {

    /**
     * Liest den eigenen Public Key (ID ist immer 1, da nur ein Datensatz).
     * Gibt null zurück, wenn noch kein Key gespeichert wurde.
     */
    @Query("SELECT * FROM own_key WHERE id = 1")
    fun getOwnKeyEntity(): DbPublicKeyEntity?

    /**
     * Fügt den eigenen Public Key ein oder aktualisiert ihn,
     * falls bereits ein Datensatz mit der ID = 1 existiert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(entity: DbPublicKeyEntity)
}
