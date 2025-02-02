package com.example.wishlistapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.resources.notes_BinScreen
import com.example.wishlistapplication.roomdb.wishlist_bin_entity
import com.example.wishlistapplication.roomdb.wishlist_table_entity
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.units.BinDialogBox
import com.example.wishlistapplication.units.NoteView
import com.example.wishlistapplication.units.Top_app_bar
import com.example.wishlistapplication.units.WishBinItem
import com.example.wishlistapplication.viewmodel.WishBinViewModel
import com.example.wishlistapplication.viewmodel.WishViewModel


@Composable
fun Bin_Screen(modifier: Modifier,
               navController: NavController,
               wishViewModel: WishViewModel,
               wishBinViewModel: WishBinViewModel){

    var binScreenState = remember{ mutableStateOf(true) }

    val wishBinList = wishBinViewModel.getAllBinWishes().collectAsState(initial = listOf())

    val dialogState = remember { mutableStateOf(false) }

    val wishOnDialogBox = remember { mutableStateOf(wishlist_bin_entity(id = 0, title = "", description = "", time = 0 , userToken = "", checkBoxState = false)) }

    var noteState by remember { mutableStateOf(true) }

    AnimatedVisibility(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(),
        visible = binScreenState.value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> -fullWidth } // Slide in from the left
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth } // Slide out to the left
        )
    ) {
        Scaffold(modifier = modifier,
            topBar = {
                Top_app_bar(title = "Bin",
                    icon = R.drawable.baseline_arrow_back_24,
                    onIconClick = {
                        binScreenState.value = false
                        navController.navigate(Routes.homeScreen)
                    }
                )
            }
        ) {
            Column(modifier = Modifier.padding(it).fillMaxSize()
                .background(MaterialTheme.colorScheme.background)) {
                LazyColumn(modifier = Modifier.padding(start = 12.dp, end = 12.dp).fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)) {
                    items(items = wishBinList.value, key = {it.id}){ wish->
                        WishBinItem(wish = wish, onClick = {
                            wishOnDialogBox.value = wish
                            dialogState.value = true
                        })
                    }
                }
            }
            BinDialogBox(dialogState = dialogState, wish = wishOnDialogBox.value,
                onDeleteClick = {
                    wishBinViewModel.deleteABinWish(wishId = wishOnDialogBox.value.id)
                    dialogState.value = false
                },
                onRestoreClick = {
                    wishViewModel.addAWish(wish = wishlist_table_entity(title = wishOnDialogBox.value.title, description = wishOnDialogBox.value.description, time = wishOnDialogBox.value.time, userToken = wishOnDialogBox.value.userToken, checkBoxState = wishOnDialogBox.value.checkBoxState))
                    wishBinViewModel.deleteABinWish(wishId = wishOnDialogBox.value.id)
                    dialogState.value = false
                })
            if (noteState) NoteView(
                noteList = notes_BinScreen,
                onCloseClicked = { noteState = false })
        }
    }
}