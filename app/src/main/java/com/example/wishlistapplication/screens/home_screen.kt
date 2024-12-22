package com.example.wishlistapplication.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.wishlistapplication.units.Top_app_bar

@Composable
fun Home_screen(modifier: Modifier) {



    // Main screen content
    Scaffold(
        modifier = modifier.clickable {
            Log.d("SCREEN","Screen Clicked !")

        },
        topBar = {
            Top_app_bar(
                onAccountIconClick = {

                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it).clickable {

        }) {
            // Screen content
            Text("1")
            Text("2")
            Text("3")
        }

    }



}
