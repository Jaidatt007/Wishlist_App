package com.example.wishlistapplication.units

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.resources.DummyData
import com.example.wishlistapplication.roomdb.wishlist_table_entity

@Composable
fun WishItem(wish : wishlist_table_entity,
             onClick : () -> Unit ){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp)
        .clickable {
            onClick()
        },elevation = CardDefaults.cardElevation(2.dp)) {

        val context = LocalContext.current
        var check_box_state by remember { mutableStateOf(false) }

        Row(modifier = Modifier.fillMaxWidth().height(52.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Checkbox(modifier = Modifier.scale(0.8f),
                checked = check_box_state,
                onCheckedChange = {
                    check_box_state = !check_box_state
                    val text = if(check_box_state) "is Completed" else "is Pending"
                    Toast.makeText(context,"${wish.title} $text !", Toast.LENGTH_SHORT).show()
                },
            )
            VerticalDivider(modifier = Modifier.height(32.dp),
                thickness = 1.dp,
                color = Color.Gray)
            Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = wish.title, fontSize = 18.sp , fontWeight = FontWeight.ExtraBold)
                    Text(text = wish.time, fontSize = 10.sp)
                }
                Text(text = wish.description , fontSize = 14.sp)
            }
        }
    }
}

@Preview
@Composable
fun temp(){
    WishItem(DummyData.wishList[0]) { }
}