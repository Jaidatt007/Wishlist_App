package com.example.wishlistapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wishlistapplication.resources.UserEmailManager
import com.example.wishlistapplication.resources.UserTokenManager
import com.google.firebase.auth.FirebaseAuth

class firebase_auth_viewmodel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    var authState: LiveData<AuthState> = _authState

    // Variable to hold the UID of the current user
    private val _userToken = MutableLiveData<String?>()
    val userToken: LiveData<String?> = _userToken

    // Variable to hold the User Email id
    private val _userEmail = MutableLiveData<String?>()
    val userEmail: LiveData<String?> = _userEmail

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            _authState.value = AuthState.UnAuthenticated
            _userToken.value = null
            _userEmail.value = null
        } else {
            _authState.value = AuthState.Authenticated
            _userToken.value = currentUser.uid
            _userEmail.value = currentUser.email
            UserTokenManager.userToken = _userToken.value.toString()
            UserEmailManager.userEmail = _userEmail.value.toString()
        }
    }

    fun explicitCheckAuthStatus(){
        checkAuthStatus()
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and Password cannot be empty!")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    _authState.value = AuthState.Authenticated
                    _userToken.value = currentUser?.uid
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong!")
                    _userToken.value = null
                }
            }
    }

    fun signup(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email and Password cannot be empty!")
            return
        }

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    _authState.value = AuthState.Authenticated
                    _userToken.value = currentUser?.uid
                    Log.d("SignUp", "Successfully SignUp!")
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong!")
                    _userToken.value = null
                }
            }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
        _userToken.value = null
        UserTokenManager.userToken = null
        UserEmailManager.userEmail = null

        Log.d("LogOut", "Log Out Successfully!")
    }
}


sealed class AuthState{
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}