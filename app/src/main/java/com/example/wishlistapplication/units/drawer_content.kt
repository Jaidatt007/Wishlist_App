package com.example.wishlistapplication.units

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel

@Composable
fun Drawer_content(modifier: Modifier,
                   navController: NavController,
                   authViewModel: firebase_auth_viewmodel
){

    val context = LocalContext.current

    Box(modifier = modifier.then(Modifier.fillMaxHeight().fillMaxWidth(0.7f)
        .background(Color.Magenta).padding(8.dp).pointerInput(Unit) {
            detectTapGestures {

            }
        })) {
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Cyan)) {

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

            Spacer(modifier = Modifier.weight(1f))

            Button(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                onClick = {
                    authViewModel.logout()
                    Toast.makeText(context,"Log Out",Toast.LENGTH_SHORT).show()
                }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Log Out", modifier = Modifier.padding(end = 8.dp))
                    Icon(painter = painterResource(R.drawable.baseline_logout_24),"Log out button !")
                }
            }
        }
    }
}

@Composable
fun Drawer_card(title:String, icon : Int, description:String , onDrawer_Item_Clicked : () -> Unit){
    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 4.dp)
        .height(40.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color.Cyan)
        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        .clickable {
            onDrawer_Item_Clicked()
        },
        verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(icon),description,
            modifier = Modifier.padding(start = 8.dp , end = 8.dp , top = 4.dp , bottom = 4.dp))
        Text(text = title, modifier = Modifier.padding(4.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}