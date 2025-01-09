package com.example.wishlistapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.resources.notes_ThemeScreen
import com.example.wishlistapplication.resources.themeList
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.units.NoteView
import com.example.wishlistapplication.units.ThemeImageCard
import com.example.wishlistapplication.units.ThemeRadioButton
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.viewmodel.ThemeViewModel

@Composable
fun ThemeScreen(modifier: Modifier,
                navController: NavController,
                themeViewModel: ThemeViewModel
){

    val themeScreenState = remember { mutableStateOf(true) }

    var noteState by remember { mutableStateOf(true) }

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(),
        visible = themeScreenState.value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth } // Slide in from the left
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth } // Slide out to the left
        )
    ) {
        Scaffold(modifier = modifier,
            topBar = {
                Top_app_bar(title = "Theme",
                    icon = R.drawable.baseline_arrow_back_24,
                    onIconClick = {
                        themeScreenState.value = false
                        navController.navigate(Routes.homeScreen)
                    })
            }
        ) {
            Column(
                modifier = Modifier.padding(it).fillMaxSize()
            ) {
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Column(modifier = Modifier.fillMaxSize()){
                    ThemeImageCard()
                    ThemeRadioButton(themeViewModel = themeViewModel)
                }
            }
        }
        if (noteState) NoteView(
            noteList = notes_ThemeScreen,
            onCloseClicked = { noteState = false })
    }
}
