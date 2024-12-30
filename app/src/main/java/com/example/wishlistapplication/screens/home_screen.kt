package com.example.wishlistapplication.screens

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapplication.resources.UserEmailManager
import com.example.wishlistapplication.resources.UserTokenManager
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.units.Drawer_content
import com.example.wishlistapplication.units.Floating_Action_Button
import com.example.wishlistapplication.units.SwipeToDeleteContainer
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.units.WishItem
import com.example.wishlistapplication.viewmodel.AuthState
import com.example.wishlistapplication.viewmodel.WishViewModel
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel
import com.example.wishlistapplication.viewmodel.user_details_viewmodel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(modifier: Modifier,
               navController: NavController,
               authViewModel: firebase_auth_viewmodel,
               wishViewModel: WishViewModel,
               userDetailsViewmodel: user_details_viewmodel
) {

    val drawerState = remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()

    val context = LocalContext.current

    var backPressedOnce by remember{ mutableStateOf(false) }

    val wishList = wishViewModel.getAllWishes().collectAsState(initial = listOf())

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.UnAuthenticated -> {
                navController.navigate(Routes.loginScreen)
            }else -> {
                UserTokenManager.userToken = authViewModel.userToken.value
                UserEmailManager.userEmail = authViewModel.userEmail.value
                authViewModel.explicitCheckAuthStatus()
            }
        }
    }


    BackHandler {
        if (backPressedOnce) {
            // Close the app
            (context as? Activity)?.finish()
        } else {
            backPressedOnce = true
            // Show a toast message
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }

    // Reset backPressedOnce after 2 seconds
    if (backPressedOnce) {
        LaunchedEffect(backPressedOnce) {
            delay(2000)
            backPressedOnce = false
        }
    }

    // Main screen content
    Scaffold(
        modifier = modifier.then(Modifier.pointerInput(Unit) {
            detectTapGestures {
                drawerState.value = false
                Toast.makeText(context, "${UserEmailManager.userEmail.toString()}",Toast.LENGTH_SHORT).show()
            }
        }),
        topBar = {
            Top_app_bar(title = "Wishlist App",
                icon = Icons.Default.AccountBox,
                onIconClick = {
                    drawerState.value = true
                }
            )
        },
        floatingActionButton = {
            Floating_Action_Button(modifier = modifier,
                navController = navController,
                authViewModel = authViewModel)
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            // Screen content
            LazyColumn(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
                items(items = wishList.value,
                    key = {it.id} ){ wish->
                    SwipeToDeleteContainer(
                        item = wish,
                        onDelete = {
                            wishViewModel.deleteAWish(wishId = it.id)
                            Log.d("Deleting","Tried to delete !")
                        },
                        animationDuration = 500
                    ) {
                        WishItem(wish = wish, onClick = {
                            val id = wish.id
                            navController.navigate(Routes.addEditScreen + "/${id}")
                        })
                    }
                }
            }
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
            authViewModel = authViewModel,
            userDetailsViewmodel = userDetailsViewmodel)
    }

}
