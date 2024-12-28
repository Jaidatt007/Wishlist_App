package com.example.wishlistapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.screens.AddEditDetailsScreen
import com.example.wishlistapplication.screens.HomeScreen
import com.example.wishlistapplication.screens.LoginScreen
import com.example.wishlistapplication.screens.SignUpScreen
import com.example.wishlistapplication.viewmodel.WishViewModel
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel

@Composable
fun AuthScreensNavigation(modifier: Modifier,
                          authViewModel : firebase_auth_viewmodel,
                          wishViewModel: WishViewModel
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
            HomeScreen(modifier = modifier,
                navController = navController,
                authViewModel = authViewModel,
                wishViewModel = wishViewModel)
        }
        composable(Routes.addeditScreen + "/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.LongType
                defaultValue = 0L
                nullable = false })) {
            val id = if(it.arguments != null) it.arguments!!.getLong("id") else 0L
            AddEditDetailsScreen(id = id,
                modifier = modifier,
                navController = navController,
                wishViewModel = wishViewModel)
        }

    }


}