package com.example.wishlistapplication.resources

import com.example.wishlistapplication.roomdb.wishlist_table_entity

object DummyData{
    val wishList = mutableListOf(
        wishlist_table_entity(title = "Wish 1", description = "Description of wish 1", time = "24/12/2024 06:12"),
        wishlist_table_entity(title = "Wish 2", description = "Description of wish 2", time = "25/12/2024 15:22"),
        wishlist_table_entity(title = "Wish 3", description = "Description of wish 3", time = "26/12/2024 23:55"),
        wishlist_table_entity(title = "Wish 4", description = "Description of wish 4", time = "27/12/2024 01:43")
    )
}