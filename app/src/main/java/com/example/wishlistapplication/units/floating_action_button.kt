package com.example.wishlistapplication.units

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wishlistapplication.R

@Composable
fun Floating_Action_Button(){
    FloatingActionButton(modifier = Modifier.padding(end = 20.dp),
        onClick = {

        },
        elevation = FloatingActionButtonDefaults.elevation(4.dp)) {
        Icon(painter = painterResource(R.drawable.baseline_add_24),"Add Wish floating action button")
    }
}
