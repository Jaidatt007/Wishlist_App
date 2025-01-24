package com.example.wishlistapplication.units

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.roomdb.wishlist_bin_entity

@Composable
fun BinDialogBox(dialogState:MutableState<Boolean>,
                 wish:wishlist_bin_entity,
                 onDeleteClick : () -> Unit,
                 onRestoreClick : () -> Unit) {
    if(dialogState.value) {
        Surface(
            modifier = Modifier.fillMaxSize()
                .pointerInput(Unit){
                    detectTapGestures {
                        dialogState.value = false
                    }
                },
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                        .clip(shape = RoundedCornerShape(16.dp))
                        .border(
                            BorderStroke(2.dp, MaterialTheme.colorScheme.onSecondary),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(MaterialTheme.colorScheme.secondary)
                        .pointerInput(Unit){
                            detectTapGestures {
                            }
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().alpha(0.7f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = wish.time, fontSize = 12.sp, textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = "Title  : ", fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.fillMaxWidth(0.24f),
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                                Text(
                                    text = wish.title, modifier = Modifier.fillMaxWidth(1f)
                                        .padding(end = 4.dp),
                                    softWrap = false, overflow = TextOverflow.Clip,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "Desc : ", fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.fillMaxWidth(0.24f),
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    softWrap = true,
                                    overflow = TextOverflow.Clip
                                )
                                Text(
                                    text = wish.description,
                                    modifier = Modifier.fillMaxWidth(1f)
                                        .padding(top = 2.dp, end = 4.dp),
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                            Button(modifier = Modifier.padding(4.dp)
                                .height(42.dp).fillMaxWidth(0.46f),
                                onClick = {
                                    onDeleteClick()
                                }
                            ) {
                                Text(text = "Delete")
                            }
                            Button(modifier = Modifier.padding(4.dp)
                                .height(42.dp).fillMaxHeight(1f),
                                onClick = {
                                    onRestoreClick()
                                }
                            ) {
                                Text(text = "Restore")
                            }
                        }
                    }
                }
            }
        }
    }
}