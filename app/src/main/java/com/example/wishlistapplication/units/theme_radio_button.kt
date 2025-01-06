package com.example.wishlistapplication.units

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wishlistapplication.viewmodel.ThemeViewModel

@Composable
fun ThemeRadioButton(themeViewModel: ThemeViewModel) {

    val rb1 = themeViewModel.radioButton1.observeAsState()
    val rb2 = themeViewModel.radioButton2.observeAsState()
    val rb3 = themeViewModel.radioButton3.observeAsState()

    Row(modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            RadioButton(selected = rb1.value ?: false, onClick = {
                themeViewModel.setRadioButton1(true)
                themeViewModel.setRadioButton2(false)
                themeViewModel.setRadioButton3(false)
            })
            Text("Light")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            RadioButton(selected = rb2.value ?: false, onClick = {
                themeViewModel.setRadioButton1(false)
                themeViewModel.setRadioButton2(true)
                themeViewModel.setRadioButton3(false)
            })
            Text("Warm")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            RadioButton(selected = rb3.value ?: false, onClick = {
                themeViewModel.setRadioButton1(false)
                themeViewModel.setRadioButton2(false)
                themeViewModel.setRadioButton3(true)
            })
            Text("Dark")
        }
    }
}

