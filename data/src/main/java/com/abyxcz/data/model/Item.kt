package com.abyxcz.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID



data class Item @JvmOverloads constructor(
    var itemId: String = "",
    var title: String = "",
    var description: String = "",
    var isCompleted: Boolean = false,
) {

}