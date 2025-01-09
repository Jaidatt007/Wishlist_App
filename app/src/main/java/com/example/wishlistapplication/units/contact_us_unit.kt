package com.example.wishlistapplication.units

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Contact_Us_Unit(icon:Int,
                    logoDetail:String,
                    onClick : () -> Unit){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(32.dp))
            .clip(RoundedCornerShape(32.dp))
            .clickable {
                onClick()
            }
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(icon),"Contact on Linkedin",
            modifier = Modifier.padding(8.dp).height(40.dp).width(40.dp))
        Text("$logoDetail.com/jaidatt_kale",
            modifier = Modifier.padding(start = 4.dp , end = 16.dp))
    }

}