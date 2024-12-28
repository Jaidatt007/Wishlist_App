package com.example.wishlistapplication.units

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel

@Composable
fun Floating_Action_Button(modifier: Modifier,
                           navController: NavController,
                           authViewModel: firebase_auth_viewmodel
){
    FloatingActionButton(modifier = Modifier.padding(bottom = 16.dp, end = 8.dp),
        onClick = {
            navController.navigate(Routes.addEditScreen + "/0L")
        },
        elevation = FloatingActionButtonDefaults.elevation(4.dp)) {
        Icon(painter = painterResource(R.drawable.baseline_add_24),"Add Wish floating action button")
    }
}
