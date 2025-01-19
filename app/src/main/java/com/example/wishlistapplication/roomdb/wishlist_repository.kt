package com.example.wishlistapplication.roomdb

import kotlinx.coroutines.flow.Flow

class WishRepository(
    private val wishDao: WishDao
) {
    suspend fun addAWish(wish : wishlist_table_entity){
        wishDao.addWish(wishEntity = wish)
    }
    suspend fun deleteAWish(wishId : Long){
        wishDao.deleteWish(wishId = wishId)
    }
    suspend fun updateAWish(wish : wishlist_table_entity){
        wishDao.updateWish(wishEntity = wish)
    }
    fun getAllWishes(userToken : String) : Flow<List<wishlist_table_entity>> = wishDao.getAllWishes(userToken = userToken)

    fun getAWishById(id:Long) : Flow<wishlist_table_entity> {
        return wishDao.getWishById(id)
    }
}