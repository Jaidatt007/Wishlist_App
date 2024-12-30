package com.example.wishlistapplication.units

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.R

@Composable
fun NoteView(noteList : List<String>,
             onCloseClicked : () -> Unit){
    Column(modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.Start) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                .padding(4.dp)
                .fillMaxWidth(0.77f)
                .background(color = Color.Gray, shape = RoundedCornerShape(16.dp))
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Note :-", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Green)
                    Icon(painter = painterResource(R.drawable.baseline_close_24),"Close Note",
                        modifier = Modifier.padding(end = 4.dp)
                            .clickable {
                                onCloseClicked()
                        })
                }
                LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                    items(noteList) {
                        Text(text = it, fontSize = 8.sp, modifier = Modifier.wrapContentHeight(), color = Color.White)
                    }
                }
            }
        }
    }
}
