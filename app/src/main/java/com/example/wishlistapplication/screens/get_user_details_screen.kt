package com.example.wishlistapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel
import com.example.wishlistapplication.viewmodel.user_details_viewmodel

@Composable
fun GetUserDetails(modifier: Modifier,
                   navController: NavController,
                   userDetailsViewmodel: user_details_viewmodel
){
    Column(modifier = modifier) {
        Text(text = "Name = ${userDetailsViewmodel.userName.value}")
        Text(text = "Contact = ${userDetailsViewmodel.userContact.value}")
        Text(text = "Email = ${userDetailsViewmodel.userEmail.value}")
        Text(text = "Password = ${userDetailsViewmodel.userPassword.value}")
        Text(text = "Description = ${userDetailsViewmodel.userDescription.value}")
    }
}