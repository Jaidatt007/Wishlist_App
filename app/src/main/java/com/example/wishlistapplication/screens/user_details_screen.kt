package com.example.wishlistapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.resources.UserEmailManager
import com.example.wishlistapplication.resources.notes_UserDetailsScreen
import com.example.wishlistapplication.sharedpreferencesdatastore.readUserPreferences
import com.example.wishlistapplication.sharedpreferencesdatastore.saveUserPreferences
import com.example.wishlistapplication.units.NoteView
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.viewmodel.user_details_viewmodel
import kotlinx.coroutines.launch

@Composable
fun GetUserDetails(modifier: Modifier,
                   navController: NavController,
                   userDetailsViewmodel: user_details_viewmodel
){

    val profileScreenState = remember { mutableStateOf(true) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        readUserPreferences(context).collect { preferences ->
            userDetailsViewmodel.setUserName(preferences.name)
            userDetailsViewmodel.setUserContact(preferences.contact)
            userDetailsViewmodel.setUserDescription(preferences.description)
        }
    }

    var name = userDetailsViewmodel.userName.observeAsState()
    var contact = userDetailsViewmodel.userContact.observeAsState()
    var email = UserEmailManager.userEmail
    var description = userDetailsViewmodel.userDescription.observeAsState()

    var editState by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    var noteState by remember { mutableStateOf(true) }

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(),
        visible = profileScreenState.value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth } // Slide in from the left
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth } // Slide out to the left
        )
    ) {
        Scaffold(modifier = modifier,
            topBar = {
                Top_app_bar(title = "Profile",
                    icon = Icons.Default.ArrowBack,
                    onIconClick = {
                        profileScreenState.value = false
                        navController.navigateUp()
                    })
            }
        ) {
            Column(modifier = Modifier.padding(it).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                OutlinedTextField(
                    value = name.value?:"",
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(0.7f),
                    onValueChange = { userDetailsViewmodel.setUserName(it) },
                    label = {
                        Text("Name")
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(if(editState) R.drawable.baseline_lock_open_24 else R.drawable.baseline_lock_outline_24),"")
                    },
                    enabled = editState
                )
                OutlinedTextField(
                    value = contact.value?:"",
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(0.7f),
                    onValueChange = { userDetailsViewmodel.setUserContact(it) },
                    label = {
                        Text("Contact")
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(if(editState) R.drawable.baseline_lock_open_24 else R.drawable.baseline_lock_outline_24),"")
                    },
                    enabled = editState
                )
                OutlinedTextField(
                    value = email.toString(),
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(0.7f),
                    onValueChange = {

                    },
                    label = {
                        Text("Email")
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(R.drawable.baseline_lock_outline_24),"")
                    },
                    enabled = false
                )
                OutlinedTextField(
                    value = description.value?:"",
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(0.7f).fillMaxHeight(0.24f),
                    onValueChange = { userDetailsViewmodel.setUserDescription(it) },
                    label = {
                        Text("Description")
                    },
                    trailingIcon = {
                        Icon(painter = painterResource(if(editState) R.drawable.baseline_lock_open_24 else R.drawable.baseline_lock_outline_24),"")
                    },
                    enabled = editState
                )

                Row(modifier = Modifier.padding(24.dp).fillMaxWidth(0.7f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = {
                        editState = !editState
                    }) {
                        Text(if(editState) "Lock" else "Edit", fontSize = 16.sp, modifier = Modifier.padding(4.dp))
                        Icon(painter = painterResource(R.drawable.baseline_edit_24),"Edit Profile",
                            modifier = Modifier.size(22.dp))
                    }
                    Button(onClick = {
                        editState = false
                        navController.navigateUp()
                        scope.launch {
                            saveUserPreferences(
                                context = context,
                                name = name.value ?: "",
                                contact = contact.value ?: "",
                                description = description.value ?: ""
                            )
                        }
                    }) {
                        Text("Save", fontSize = 16.sp, modifier = Modifier.padding(4.dp))
                        Icon(painter = painterResource(R.drawable.baseline_save_24),"Edit Profile",
                            modifier = Modifier.size(22.dp))
                    }
                }
            }
            if (noteState) NoteView(noteList = notes_UserDetailsScreen, onCloseClicked = { noteState = false })
        }
    }
}