package com.example.wishlistapplication.units

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.R

@Composable
fun Drawer_content(modifier: Modifier){
    Box(modifier = modifier.then(Modifier.fillMaxHeight().fillMaxWidth(0.7f)
        .background(Color.Magenta).padding(8.dp).pointerInput(Unit) {
            detectTapGestures {

            }
        })) {
        Column(modifier = Modifier.fillMaxWidth().background(Color.Cyan)) {

            Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly){
                Icon(painter = painterResource(R.drawable.baseline_mood_24),"",
                        modifier = Modifier.height(56.dp).width(56.dp))
                Column {
                    Text(text = "Welcome,", fontSize = 16.sp)
                    Row() {
                        Text(text = "Jaidatt", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 32.dp))
                        Text(text = ".......", fontSize = 20.sp)
                    }
                }
            }
            Drawer_card("Profile",R.drawable.baseline_person_24,"Profile Icon", onDrawer_Item_Clicked = {

            })
            Drawer_card("Profile",R.drawable.baseline_person_24,"Profile Icon", onDrawer_Item_Clicked = {

            })
            Drawer_card("Profile",R.drawable.baseline_person_24,"Profile Icon", onDrawer_Item_Clicked = {

            })
            Drawer_card("Profile",R.drawable.baseline_person_24,"Profile Icon", onDrawer_Item_Clicked = {

            })
        }
    }
}

@Composable
fun Drawer_card(title:String, icon : Int, description:String , onDrawer_Item_Clicked : () -> Unit){
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)
        .height(40.dp).clip(RoundedCornerShape(8.dp)).background(Color.Green).clickable {
            onDrawer_Item_Clicked()
        },
        verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(icon),description,
            modifier = Modifier.padding(start = 8.dp , end = 8.dp , top = 4.dp , bottom = 4.dp))
        Text(text = title, modifier = Modifier.padding(4.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}