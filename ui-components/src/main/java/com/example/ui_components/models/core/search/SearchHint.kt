package com.example.ui_components.models.core.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHint(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    val hint: String = "",
    @ColumnInfo(index = true)
    val timeStamp: Long = System.currentTimeMillis(),
    @ColumnInfo(index = true)
    val type: String = "", /* Use the 'SearchHintType' enum to initialize */
    val isPreviousSearch: Boolean = false
)