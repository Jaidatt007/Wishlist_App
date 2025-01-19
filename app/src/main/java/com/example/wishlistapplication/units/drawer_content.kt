package com.example.wishlistapplication.units

import android.widget.Toast
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.sharedpreferencesdatastore.saveUserPreferences
import com.example.wishlistapplication.viewmodel.ThemeViewModel
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel
import com.example.wishlistapplication.viewmodel.user_details_viewmodel
import kotlinx.coroutines.launch

@Composable
fun Drawer_content(modifier: Modifier,
                   navController: NavController,
                   authViewModel: firebase_auth_viewmodel,
                   userDetailsViewmodel: user_details_viewmodel,
                   themeViewModel: ThemeViewModel,
                   onExitClick : () -> Unit
){

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    Surface(modifier = modifier.then(Modifier.fillMaxHeight()
        .fillMaxWidth(0.7f)
        .pointerInput(Unit) {
            detectTapGestures {
            }
        }),
        shadowElevation = 8.dp) {

        Box(modifier = Modifier.background(MaterialTheme.colorScheme.secondary)) {
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DrawerEmoji(userDetailsViewmodel.userName.value, themeViewModel = themeViewModel)
                    Column {
                        Text(text = "Welcome,", fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onTertiary)
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = if(userDetailsViewmodel.userName.value == null) "No Name" else userDetailsViewmodel.userName.value.toString(),
                                fontSize = 24.sp, fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 32.dp, end = 2.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(text = ".......", fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onTertiary)
                        }
                    }
                }
                Drawer_card(
                    title = "Profile",
                    icon = R.drawable.baseline_person_24,
                    description = "Profile",
                    horizontalPaddingValue = 8,
                    onDrawer_Item_Clicked = {
                        navController.navigate(Routes.getUserDetails)
                    }
                )
                Drawer_card(
                    title = "Notifications",
                    icon = R.drawable.baseline_notifications_24,
                    description = "Notifications",
                    horizontalPaddingValue = 8,
                    onDrawer_Item_Clicked = {

                    }
                )
                Drawer_card(
                    title = "Theme",
                    icon = R.drawable.baseline_color_lens_24,
                    description = "Theme",
                    horizontalPaddingValue = 8,
                    onDrawer_Item_Clicked = {
                        navController.navigate(Routes.themeScreen)
                    }
                )
                Drawer_card(
                    title = "Bin",
                    icon = R.drawable.baseline_delete_24,
                    description = "Bin",
                    horizontalPaddingValue = 8,
                    onDrawer_Item_Clicked = {
                        navController.navigate(Routes.binScreen)
                    }
                )
                Drawer_card(
                    title = "Settings",
                    icon = R.drawable.baseline_settings_24,
                    description = "Settings",
                    horizontalPaddingValue = 8,
                    onDrawer_Item_Clicked = {

                    }
                )
                Drawer_card(
                    title = "FAQ",
                    icon = R.drawable.baseline_question_answer_24,
                    description = "FAQ",
                    horizontalPaddingValue = 8,
                    onDrawer_Item_Clicked = {

                    }
                )
                Drawer_card(
                    title = "Contact Us",
                    icon = R.drawable.baseline_connect_without_contact_24,
                    description = "Contact Us",
                    horizontalPaddingValue = 8,
                    onDrawer_Item_Clicked = {
                        navController.navigate(Routes.contactUsScreen)
                    }
                )
                Drawer_card(
                    title = "Share",
                    icon = R.drawable.baseline_share_24,
                    description = "Share",
                    horizontalPaddingValue = 40,
                    onDrawer_Item_Clicked = {

                    }
                )
                Drawer_card(
                    title = "Exit",
                    icon = R.drawable.baseline_exit_to_app_24,
                    description = "Exit",
                    horizontalPaddingValue = 80,
                    onDrawer_Item_Clicked = {
                        onExitClick()
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp),
                    onClick = {
                        authViewModel.logout()
                        scope.launch {
                            saveUserPreferences(
                                context = context,
                                name = "",
                                contact = "",
                                description = ""
                            )
                        }
                        Toast.makeText(context, "Log Out", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Log Out", modifier = Modifier.padding(end = 8.dp))
                        Icon(
                            painter = painterResource(R.drawable.baseline_logout_24),
                            "Log out button !"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Drawer_card(title:String,
                icon : Int,
                description:String ,
                horizontalPaddingValue : Int,
                onDrawer_Item_Clicked : () -> Unit){
    Row(modifier = Modifier.fillMaxWidth()
        .padding(horizontal = horizontalPaddingValue.dp, vertical = 4.dp)
        .heightIn(min = 40.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.background)
        .border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(8.dp))
        .clickable {
            onDrawer_Item_Clicked()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if(title == "Exit" || title == "Share") Arrangement.Center else Arrangement.Start)  {
        Icon(painter = painterResource(icon),description,
            modifier = Modifier.padding(start = 8.dp , end = 8.dp , top = 4.dp , bottom = 4.dp),
            tint = MaterialTheme.colorScheme.onBackground)
        Text(text = title, modifier = Modifier.padding(4.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground)
    }
}
