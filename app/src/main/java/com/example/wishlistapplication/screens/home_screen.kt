package com.example.wishlistapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Home_screen(modifier: Modifier) {

    // Main screen content
    Scaffold(
        topBar = {}
    ) {
        Column(modifier = Modifier.padding(it)) {
            // Screen content
            Text("1")
            Text("2")
            Text("3")
        }

    }


}
