package com.example.wishlistapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.units.Drawer_content
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.viewmodel.AuthState
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel

@Composable
fun Home_screen(modifier: Modifier,
                navController: NavController,
                authViewModel: firebase_auth_viewmodel) {

    val drawerState = remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.UnAuthenticated -> {
                navController.navigate(Routes.loginScreen)
            }else -> {
            Unit
        }
        }
    }

    // Main screen content
    Scaffold(
        modifier = modifier.then(Modifier.pointerInput(Unit) {
            detectTapGestures {
                drawerState.value = false
            }
        }),
        topBar = {
            Top_app_bar(
                onAccountIconClick = {
                    drawerState.value = true
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            // Screen content
            Text("1")
            Text("2")
            Text("3")
        }

    }

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth(0.7f)
            .fillMaxHeight(),
        visible = drawerState.value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth } // Slide in from the left
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth } // Slide out to the left
        )
    ) {
        Drawer_content(modifier = modifier,
            navController = navController,
            authViewModel = authViewModel)
    }

}
