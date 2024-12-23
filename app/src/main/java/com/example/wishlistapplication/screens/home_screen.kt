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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.example.wishlistapplication.units.Drawer_content
import com.example.wishlistapplication.units.Top_app_bar

@Composable
fun Home_screen(modifier: Modifier) {

    val drawerState = remember { mutableStateOf(false) }

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
        Drawer_content(modifier = modifier)
    }

}
