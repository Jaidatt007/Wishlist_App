package com.example.wishlistapplication.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.resources.getCurrentDateTime
import com.example.wishlistapplication.roomdb.wishlist_table_entity
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.viewmodel.WishViewModel

@Composable
fun AddEditDetailsScreen(modifier: Modifier,
                         navController: NavController,
                         wishViewModel: WishViewModel
){

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(modifier = modifier,
        topBar = {
            Top_app_bar(title = "Add/Edit Details",
                icon = Icons.Default.ArrowBack,
                onIconClick = {
                    navController.navigateUp()
                })
        }
    ) { innerPadding->
        Column(modifier = Modifier.padding(innerPadding).padding(start = 24.dp, end = 24.dp)) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                value = title,
                label = {
                    Text(text = "Title")
                        },
                onValueChange = {
                    title=it
                }
            )
            OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
                value = description,
                label = {
                    Text(text = "Description")
                        },
                onValueChange = {
                    description=it
                }
            )
            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {
                    title=""
                    description=""
                    Toast.makeText(context,"Values Resetted !",Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Reset", fontSize = 16.sp, modifier = Modifier.padding(4.dp))
                    Icon(painter = painterResource(R.drawable.baseline_refresh_24), contentDescription = "Reset values",
                        modifier = Modifier.size(24.dp))
                }
                Button(onClick = {
                    wishViewModel.addAWish(wish = wishlist_table_entity(title = title, description = description, time = getCurrentDateTime()))
                    navController.navigateUp()
                    Toast.makeText(context,"New Wish Added !",Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Add/Edit", fontSize = 16.sp, modifier = Modifier.padding(4.dp))
                    Icon(painter = painterResource(R.drawable.baseline_edit_24),"Add/Edit data",
                        modifier = Modifier.size(22.dp))
                }
            }

        }
    }
}
