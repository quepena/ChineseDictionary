package com.example.chinesedictionary.roomdb

import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addData(favoriteList: FavoriteList)

    @Query("select * from favoritelist")
    fun getFavoriteData(): List<FavoriteList>

    @Query("SELECT EXISTS (SELECT 1 FROM favoritelist WHERE id=:id)")
    fun isFavorite(id: String): Int

    @Delete
    fun delete(favoriteList: FavoriteList?)
}