package com.example.wishlistapplication.roomdb

import kotlinx.coroutines.flow.Flow

class WishBinRepository(
    private val wishBinDao: WishBinDao
) {
    suspend fun addABinWish(wish : wishlist_bin_entity){
        wishBinDao.addWish(wishBinEntity = wish)
    }
    suspend fun deleteABinWish(wishId : Long){
        wishBinDao.deleteWish(wishId = wishId)
    }
    suspend fun updateABinWish(wish : wishlist_bin_entity){
        wishBinDao.updateWish(wishBinEntity = wish)
    }
    fun getAllBinWishes(userToken : String) : Flow<List<wishlist_bin_entity>> = wishBinDao.getAllWishes(userToken = userToken)

    fun getABinWishById(id:Long) : Flow<wishlist_bin_entity> {
        return wishBinDao.getWishById(id)
    }
}