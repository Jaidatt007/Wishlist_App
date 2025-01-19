package com.example.wishlistapplication.roomdb

import android.content.Context
import androidx.room.Room

object wishlist_graph {
    lateinit var database : WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    val wishBinRepository by lazy {
        WishBinRepository(wishBinDao = database.wishBinDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,
            WishDatabase::class.java,
            name = "wishlist.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}