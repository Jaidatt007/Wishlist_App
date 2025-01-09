package com.example.wishlistapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.units.Contact_Us_Unit
import com.example.wishlistapplication.units.Top_app_bar

@Composable
fun ContactUsScreen(modifier: Modifier,
                    navController: NavController){

    val contactUsScreenState = remember { mutableStateOf(true) }


    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(),
        visible = contactUsScreenState.value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth } // Slide in from the left
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth } // Slide out to the left
        )
    ) {
        Scaffold(modifier = modifier,
            topBar = {
                Top_app_bar(title = "Contact Us", icon = R.drawable.baseline_arrow_back_24,
                    onIconClick = {
                        contactUsScreenState.value = false
                        navController.navigate(Routes.homeScreen)
                    })
            }
        ) {
            Column(modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.padding(top = 40.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Contact_Us_Unit(
                        icon = R.drawable.github,
                        logoDetail = "github",
                        onClick = {
                        }
                    )
                    Contact_Us_Unit(
                        icon = R.drawable.linkedin,
                        logoDetail = "linkedin",
                        onClick = {
                        }
                    )
                    Contact_Us_Unit(
                        icon = R.drawable.gmail,
                        logoDetail = "gmail",
                        onClick = {

                        }
                    )
                    Contact_Us_Unit(
                        icon = R.drawable.instagram,
                        logoDetail = "instagram",
                        onClick = {

                        }
                    )
                    Contact_Us_Unit(
                        icon = R.drawable.whatsapp,
                        logoDetail = "whatsapp",
                        onClick = {

                        }
                    )
                    Contact_Us_Unit(
                        icon = R.drawable.facebook,
                        logoDetail = "facebook",
                        onClick = {
                        }
                    )
                }
            }
        }
    }

}