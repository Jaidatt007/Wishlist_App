package com.example.wishlistapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.wishlistapplication.navigation.ScreensNavigations
import com.example.wishlistapplication.ui.theme.WishlistApplicationTheme
import com.example.wishlistapplication.viewmodel.WishViewModel
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel
import com.example.wishlistapplication.viewmodel.user_details_viewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel : firebase_auth_viewmodel by viewModels()
        val wishlistViewModel : WishViewModel by viewModels()
        val userDetailsViewModel : user_details_viewmodel by viewModels()
        setContent {
            WishlistApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreensNavigations(modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel,
                        wishViewModel = wishlistViewModel,
                        userDetailsViewmodel = userDetailsViewModel)
                }
            }
        }
    }
}

