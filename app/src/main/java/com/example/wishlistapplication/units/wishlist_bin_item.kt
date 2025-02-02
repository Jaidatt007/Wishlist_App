package com.example.wishlistapplication.units

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.resources.getTimeFromMillis
import com.example.wishlistapplication.roomdb.wishlist_bin_entity


@Composable
fun WishBinItem(wish : wishlist_bin_entity,
                onClick : () -> Unit ){
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .defaultMinSize(minHeight = 52.dp)
        .padding(top = 12.dp)
        .clickable {
            onClick()
        },
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondary.copy(alpha = 3f))
    ) {

        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()
            .background(MaterialTheme.colorScheme.tertiary),
            verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp).fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Title : ", fontSize = 18.sp ,
                        modifier = Modifier,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onTertiary)
                    Text(text = wish.title, fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        softWrap = false,
                        modifier = Modifier.fillMaxWidth(0.6f),
                        color = MaterialTheme.colorScheme.onTertiary,
                        maxLines = 1)
                    Text(text = getTimeFromMillis(wish.time), fontSize = 10.sp,
                        modifier = Modifier.fillMaxWidth(1f),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f),
                        maxLines = 1)
                }
                Row(verticalAlignment = Alignment.Top) {
                    Text(text = "Desc : ", fontSize = 18.sp ,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onTertiary)
                    Text(text = wish.description , fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 2.dp),
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        softWrap = true,
                        color = MaterialTheme.colorScheme.onTertiary)
                }

            }
        }
    }
}
