package com.example.wishlistapplication.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addWish(wishEntity:wishlist_table_entity)

    @Query("DELETE FROM `Wishlist_Table` WHERE ID = :wishId")
    abstract fun deleteWish(wishId : Long)

    @Update
    abstract fun updateWish(wishEntity: wishlist_table_entity)

    @Query("SELECT * FROM `Wishlist_Table` WHERE User_Token = :userToken")
    abstract fun getAllWishes(userToken : String) : Flow<List<wishlist_table_entity>>

    @Query("SELECT * FROM `Wishlist_Table` WHERE ID = :id")
    abstract fun getWishById(id:Long) : Flow<wishlist_table_entity>

}