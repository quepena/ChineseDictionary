package com.example.chinesedictionary.roomdb

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritelist")
class FavoriteList {
    @NonNull
    @PrimaryKey
    var id: String = ""

    @ColumnInfo(name = "character")
    var character: String? = ""

    @ColumnInfo(name = "pinyin")
    var pinyin: String? = ""

    @ColumnInfo(name = "translation")
    var translation: String? = ""
}