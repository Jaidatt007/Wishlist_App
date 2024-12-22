package com.example.wishlistapplication.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import com.example.wishlistapplication.units.Top_app_bar

@Composable
fun Home_screen(modifier: Modifier) {

    val drawerState = remember { mutableStateOf(false) }

    // Main screen content
    Scaffold(
        modifier = modifier.clickable {
            Log.d("SCREEN","Screen Clicked !")
            drawerState.value = false
        },
        topBar = {
            Top_app_bar(
                onAccountIconClick = {
                    drawerState.value = true
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it).clickable {
            drawerState.value = false
        }) {
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
        Box(modifier = modifier.then(Modifier.fillMaxHeight().fillMaxWidth(0.7f)
            .background(Color.Magenta).clickable {
                Log.d("DRAWER","Drawer Clicked !")
            })) {

        }
    }

}
