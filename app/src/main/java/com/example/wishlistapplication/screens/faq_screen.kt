package com.example.wishlistapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.resources.faq_Data_Item_List
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.units.FAQ_Unit
import com.example.wishlistapplication.units.Top_app_bar

@Composable
fun FAQ_Screen(modifier: Modifier,
               navController: NavController){

    val faqScreenState = remember { mutableStateOf(true) }

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(),
        visible = faqScreenState.value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth } // Slide in from the left
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth } // Slide out to the left
        )
    ) {
        Scaffold(modifier = modifier,
            topBar = {
                Top_app_bar(title = "FAQ",
                    icon = R.drawable.baseline_arrow_back_24,
                    onIconClick = {
                        faqScreenState.value = false
                        navController.navigate(Routes.homeScreen)
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it).fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn {
                    items(faq_Data_Item_List){
                        FAQ_Unit(it)
                    }
                }
            }
        }
    }
}