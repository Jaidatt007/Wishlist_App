package com.example.wishlistapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.screens.Home_screen
import com.example.wishlistapplication.screens.LoginScreen
import com.example.wishlistapplication.screens.SignUpScreen
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel

@Composable
fun AuthScreensNavigation(modifier: Modifier,
                          authViewModel : firebase_auth_viewmodel
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.loginScreen) {
        composable(Routes.loginScreen){
            LoginScreen(modifier = modifier,
                navController = navController,
                authViewModel = authViewModel)
        }
        composable(Routes.signUpScreen){
            SignUpScreen(modifier = modifier,
                navController = navController,
                authViewModel = authViewModel)
        }
        composable(Routes.homeScreen){
            Home_screen(modifier = modifier,
                navController = navController,
                authViewModel = authViewModel)
        }

    }


}