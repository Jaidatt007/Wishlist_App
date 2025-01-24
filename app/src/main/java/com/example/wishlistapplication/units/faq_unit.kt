package com.example.wishlistapplication.units

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.R
import com.example.wishlistapplication.resources.FAQ_Data_Item

@Composable
fun FAQ_Unit(faq:FAQ_Data_Item){
    var openAnswer by remember { mutableStateOf(false)}

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Column() {
            Icon(
                painter = painterResource(R.drawable.baseline_add_circle_outline_24), "FAQ Answer",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(28.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        if(openAnswer){
                            MaterialTheme.colorScheme.primary
                        }else{
                            MaterialTheme.colorScheme.error
                        }
                    )
                    .clickable {
                    openAnswer = !openAnswer
                    },
                tint = if(openAnswer){
                            MaterialTheme.colorScheme.onPrimary
                        }else{
                            MaterialTheme.colorScheme.onError
                        }
            )
        }
        Column() {
            Text(
                text = faq.question, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                fontSize = 20.sp
            )
            if(openAnswer){
                Text(text = faq.answer,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify)
            }
        }
    }

}