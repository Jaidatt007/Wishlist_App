package com.example.wishlistapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.wishlistapplication.navigation.ScreensNavigations
import com.example.wishlistapplication.ui.theme.WishlistApplicationTheme
import com.example.wishlistapplication.viewmodel.ThemeViewModel
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
        val themeViewModel : ThemeViewModel by viewModels()
        setContent {
            WishlistApplicationTheme(
                lightTheme = themeViewModel.radioButton1.observeAsState().value ?: false,
                warmTheme = themeViewModel.radioButton2.observeAsState().value ?: false,
                darkTheme = themeViewModel.radioButton3.observeAsState().value ?: false
            ) {
                Log.d("ligth", "onCreate: ${themeViewModel.radioButton1.value}")
                Log.d("warm", "onCreate: ${themeViewModel.radioButton2.value}")
                Log.d("dark", "onCreate: ${themeViewModel.radioButton3.value}")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreensNavigations(modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel,
                        wishViewModel = wishlistViewModel,
                        userDetailsViewmodel = userDetailsViewModel,
                        themeViewModel = themeViewModel
                    )
                }
            }
        }
    }
}

