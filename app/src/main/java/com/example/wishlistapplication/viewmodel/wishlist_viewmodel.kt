package com.example.wishlistapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapplication.resources.UserTokenManager
import com.example.wishlistapplication.roomdb.WishRepository
import com.example.wishlistapplication.roomdb.wishlist_graph
import com.example.wishlistapplication.roomdb.wishlist_table_entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val wishRepository: WishRepository = wishlist_graph.wishRepository ) : ViewModel() {

    lateinit var getAllWishes : Flow<List<wishlist_table_entity>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes(UserTokenManager.userToken.toString())
        }
    }


    fun getAllWishes() : Flow<List<wishlist_table_entity>> {
        return wishRepository.getAllWishes(UserTokenManager.userToken.toString())
    }

    fun getAWishById(id:Long) : Flow<wishlist_table_entity> {
        return wishRepository.getAWishById(id)
    }
    fun addAWish(wish: wishlist_table_entity){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addAWish(wish = wish)
        }
    }
    fun deleteAWish(wishId:Long){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteAWish(wishId = wishId)
        }
    }
    fun updateAWish(wish: wishlist_table_entity) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish)
        }
    }
}