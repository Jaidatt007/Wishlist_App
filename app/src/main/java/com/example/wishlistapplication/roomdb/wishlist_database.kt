package com.example.wishlistapplication.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [wishlist_table_entity::class,wishlist_bin_entity::class],
    version = 2,
    exportSchema = false
)
abstract class WishDatabase : RoomDatabase(){
    abstract fun wishDao() : WishDao
    abstract fun wishBinDao() : WishBinDao
}