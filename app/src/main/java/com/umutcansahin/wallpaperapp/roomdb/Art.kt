package com.umutcansahin.wallpaperapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art (
    var imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)
