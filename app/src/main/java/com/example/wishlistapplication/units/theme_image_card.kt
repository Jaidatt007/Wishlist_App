package com.example.wishlistapplication.units

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wishlistapplication.R

@Composable
fun ThemeImageCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(R.drawable.light_theme), "Light Theme",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .height(249.dp).width(112.dp).clip(RoundedCornerShape(8.dp))
                .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
        )
        Image(
            painter = painterResource(R.drawable.warm_theme), "Warm Theme",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .height(249.dp).width(112.dp).clip(RoundedCornerShape(8.dp))
                .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
        )
        Image(
            painter = painterResource(R.drawable.dark_theme), "Dark Theme",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .height(249.dp).width(112.dp).clip(RoundedCornerShape(8.dp))
                .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
        )
    }
}