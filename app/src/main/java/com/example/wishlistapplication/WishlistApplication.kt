package com.example.wishlistapplication

import android.app.Application
import com.example.wishlistapplication.roomdb.wishlist_graph

class WishlistApp : Application() {
    override fun onCreate() {
        super.onCreate()
        wishlist_graph.provide(this)
    }
}