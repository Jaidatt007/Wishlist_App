package com.example.wishlistapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class user_details_viewmodel : ViewModel() {

    //user name
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun setUserName(name : String){
        _userName.value = name
    }

    //user contact
    private val _userContact = MutableLiveData<String>()
    val userContact: LiveData<String> = _userContact

    fun setUserContact(contact : String){
        _userContact.value = contact
    }

    //user email
    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> = _userEmail

    fun setUserEmail(email : String){
        _userEmail.value = email
    }

    //user password
    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String> = _userPassword

    fun setUserPassword(password : String){
        _userPassword.value = password
    }

    //user description
    private val _userDescription = MutableLiveData<String>()
    val userDescription: LiveData<String> = _userDescription

    fun setUserDescription(description : String){
        _userDescription.value = description
    }


}