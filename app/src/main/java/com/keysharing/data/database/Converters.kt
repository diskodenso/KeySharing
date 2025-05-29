// File: app/src/main/java/com/keysharing/data/database/Converters.kt
package com.keysharing.data.database

import androidx.room.TypeConverter
import com.keysharing.domain.NotificationType
import com.keysharing.domain.TransferMethod

class Converters {

    // TransferMethod ↔ String
    @TypeConverter
    fun fromTransferMethod(method: TransferMethod): String =
        method.name

    @TypeConverter
    fun toTransferMethod(data: String): TransferMethod =
        TransferMethod.valueOf(data)

    // NotificationType ↔ String
    @TypeConverter
    fun fromNotificationType(type: NotificationType): String =
        type.name

    @TypeConverter
    fun toNotificationType(data: String): NotificationType =
        NotificationType.valueOf(data)
}
