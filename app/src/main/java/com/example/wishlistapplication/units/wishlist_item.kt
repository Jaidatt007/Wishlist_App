package com.example.wishlistapplication.units

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlistapplication.resources.getTimeFromMillis
import com.example.wishlistapplication.roomdb.wishlist_table_entity

@Composable
fun WishItem(wish : wishlist_table_entity,
             onCheckBoxClick : (Boolean) -> Unit,
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

        val context = LocalContext.current

        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()
            .background(MaterialTheme.colorScheme.tertiary),
            verticalAlignment = Alignment.CenterVertically) {
            Checkbox(modifier = Modifier.scale(0.8f),
                checked = wish.checkBoxState,
                onCheckedChange = {
                    onCheckBoxClick(!wish.checkBoxState)
                    val text = if(!wish.checkBoxState) "is Completed" else "is Pending"
                    Toast.makeText(context,"${wish.title} $text !", Toast.LENGTH_SHORT).show()
                },
                colors = CheckboxColors(
                    checkedCheckmarkColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedCheckmarkColor = MaterialTheme.colorScheme.onTertiary,
                    checkedBoxColor = MaterialTheme.colorScheme.primary,
                    uncheckedBoxColor = MaterialTheme.colorScheme.tertiary,
                    checkedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    uncheckedBorderColor = MaterialTheme.colorScheme.onSecondary,
                    disabledCheckedBoxColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                    disabledUncheckedBoxColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
                    disabledIndeterminateBoxColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f),
                    disabledBorderColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f),
                    disabledUncheckedBorderColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.4f),
                    disabledIndeterminateBorderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.4f)
                )
            )
            VerticalDivider(modifier = Modifier.height(32.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.3f))
            Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp).fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = wish.title, fontSize = 18.sp ,
                        fontWeight = FontWeight.ExtraBold,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = false,
                        modifier = Modifier.fillMaxWidth(0.6f),
                        color = MaterialTheme.colorScheme.onTertiary,
                        maxLines = 1)
                    Text(text =  getTimeFromMillis(wish.time), fontSize = 10.sp,
                        modifier = Modifier.fillMaxWidth(1f),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f),
                        maxLines = 1)
                }
                Text(text = wish.description , fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 2.dp),
                    color = MaterialTheme.colorScheme.onTertiary)
            }
        }
    }
}
