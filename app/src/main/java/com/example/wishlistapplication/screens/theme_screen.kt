package com.example.wishlistapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.wishlistapplication.resources.notes_ThemeScreen
import com.example.wishlistapplication.sharedpreferencesdatastore.readUserPreferences
import com.example.wishlistapplication.units.NoteView
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.viewmodel.user_details_viewmodel

@Composable
fun ThemeScreen(modifier: Modifier,
                   navController: NavController
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
                    icon = Icons.Default.ArrowBack,
                    onIconClick = {
                        themeScreenState.value = false
                        navController.navigateUp()
                    })
            }
        ) {
            Column(modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {


                }
            }
            if (noteState) NoteView(noteList = notes_ThemeScreen, onCloseClicked = { noteState = false })
        }
    }