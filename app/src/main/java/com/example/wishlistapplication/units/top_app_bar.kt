package com.example.wishlistapplication.units

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Top_app_bar(title : String,
                icon : ImageVector,
                onIconClick : () -> Unit){
    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary)
        .fillMaxHeight(0.077f),
        contentAlignment = Alignment.Center) {

        val context = LocalContext.current

        Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)) {
            Icon(imageVector = icon, contentDescription = "Account Icon",
                modifier = Modifier.scale(1.4f).clickable{
//                    Toast.makeText(context,"This is Account Icon !",Toast.LENGTH_SHORT).show()
                    onIconClick()
                },
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center) {
            Text(text = title , fontSize = 32.sp , fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive,
                modifier = Modifier.clickable {
                    Toast.makeText(context,"This is Wishlist App !",Toast.LENGTH_SHORT).show()
                },
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}