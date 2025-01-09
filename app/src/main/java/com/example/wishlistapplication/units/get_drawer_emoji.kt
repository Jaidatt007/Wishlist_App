package com.example.wishlistapplication.units

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.viewmodel.ThemeViewModel

@Composable
fun DrawerEmoji(name:String?,
                themeViewModel: ThemeViewModel){

    val lightTheme = themeViewModel.radioButton1.observeAsState().value ?: false
    val warmTheme = themeViewModel.radioButton2.observeAsState().value ?: false
    val darkTheme = themeViewModel.radioButton3.observeAsState().value ?: false

    val emoji:String
    var toastMessage:String

    val context = LocalContext.current

    if(name == null || name.trim() == "") {
        emoji = "😞"
        toastMessage = "Please add your Profile details ! " + emoji
    }else {
        when {
            lightTheme -> {
                emoji = "😇"
                toastMessage = emoji + " I am Light !"
            }

            warmTheme -> {
                emoji = "🤗"
                toastMessage = emoji + " I am Warm !"
            }

            darkTheme -> {
                emoji = "😎"
                toastMessage = emoji + " I am Cool !"
            }

            else -> {
                if (isSystemInDarkTheme()) {
                    emoji = "😎"
                    toastMessage = emoji + " I am Cool !"
                } else {
                    emoji = "😇"
                    toastMessage = emoji + " I am Light !"
                }
            }
        }
    }

    Text(
        text = emoji,
        fontSize = 48.sp,
        modifier = Modifier
            .height(56.dp)
            .width(56.dp)
            .clickable {
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            }
    )

}