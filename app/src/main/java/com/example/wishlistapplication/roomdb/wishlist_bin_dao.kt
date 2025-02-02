package com.example.wishlistapplication.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishBinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addWish(wishBinEntity:wishlist_bin_entity)

    @Query("DELETE FROM `Wishlist_Bin` WHERE ID = :wishId")
    abstract fun deleteWish(wishId : Long)

    @Update
    abstract fun updateWish(wishBinEntity: wishlist_bin_entity)

    @Query("SELECT * FROM `Wishlist_Bin` WHERE User_Token = :userToken ORDER BY Wish_Created_Time ASC")
    abstract fun getAllWishes(userToken : String) : Flow<List<wishlist_bin_entity>>

    @Query("SELECT * FROM `Wishlist_Bin` WHERE ID = :id")
    abstract fun getWishById(id:Long) : Flow<wishlist_bin_entity>

}