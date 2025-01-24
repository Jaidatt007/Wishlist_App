package com.example.wishlistapplication.units

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wishlistapplication.R
import com.example.wishlistapplication.viewmodel.ThemeViewModel

@Composable
fun ThemeImageCard(themeViewModel: ThemeViewModel) {

    val rb1 = themeViewModel.radioButton1.observeAsState()
    val rb2 = themeViewModel.radioButton2.observeAsState()
    val rb3 = themeViewModel.radioButton3.observeAsState()

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
                .clickable {
                    themeViewModel.setRadioButton1(true)
                    themeViewModel.setRadioButton2(false)
                    themeViewModel.setRadioButton3(false)
                }
        )
        Image(
            painter = painterResource(R.drawable.warm_theme), "Warm Theme",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .height(249.dp).width(112.dp).clip(RoundedCornerShape(8.dp))
                .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
                .clickable {
                    themeViewModel.setRadioButton1(false)
                    themeViewModel.setRadioButton2(true)
                    themeViewModel.setRadioButton3(false)
                }
        )
        Image(
            painter = painterResource(R.drawable.dark_theme), "Dark Theme",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .height(249.dp).width(112.dp).clip(RoundedCornerShape(8.dp))
                .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
                .clickable {
                    themeViewModel.setRadioButton1(false)
                    themeViewModel.setRadioButton2(false)
                    themeViewModel.setRadioButton3(true)
                }
        )
    }
}