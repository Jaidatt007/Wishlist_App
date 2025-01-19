package com.example.wishlistapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapplication.resources.UserTokenManager
import com.example.wishlistapplication.roomdb.WishBinRepository
import com.example.wishlistapplication.roomdb.wishlist_bin_entity
import com.example.wishlistapplication.roomdb.wishlist_graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishBinViewModel(private val wishBinRepository: WishBinRepository  = wishlist_graph.wishBinRepository ) : ViewModel() {

    lateinit var getAllBinWishes : Flow<List<wishlist_bin_entity>>

    init {
        viewModelScope.launch {
            getAllBinWishes = wishBinRepository.getAllBinWishes(UserTokenManager.userToken.toString())
        }
    }

    fun getAllBinWishes() : Flow<List<wishlist_bin_entity>> {
        return wishBinRepository.getAllBinWishes(UserTokenManager.userToken.toString())
    }

    fun getABinWishById(id:Long) : Flow<wishlist_bin_entity> {
        return wishBinRepository.getABinWishById(id)
    }

    fun addABinWish(wish: wishlist_bin_entity){
        viewModelScope.launch(Dispatchers.IO) {
            wishBinRepository.addABinWish(wish = wish)
        }
    }
    fun deleteABinWish(wishId:Long){
        viewModelScope.launch(Dispatchers.IO) {
            wishBinRepository.deleteABinWish(wishId = wishId)
        }
    }
    fun updateABinWish(wish: wishlist_bin_entity) {
        viewModelScope.launch(Dispatchers.IO) {
            wishBinRepository.updateABinWish(wish)
        }
    }
}