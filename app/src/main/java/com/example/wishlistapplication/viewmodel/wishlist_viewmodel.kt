package com.example.wishlistapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapplication.roomdb.WishRepository
import com.example.wishlistapplication.roomdb.wishlist_graph
import com.example.wishlistapplication.roomdb.wishlist_table_entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val wishRepository: WishRepository = wishlist_graph.wishRepository ) : ViewModel() {

    var  wishTitleState by mutableStateOf(value = "")
    var  wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newWishTitle : String){
        wishTitleState = newWishTitle
    }
    fun onWishDescriptionChanged(newWishDescription : String){
        wishDescriptionState = newWishDescription
    }

    lateinit var getAllWishes : Flow<List<wishlist_table_entity>>
    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }
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