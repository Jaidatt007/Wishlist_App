package com.example.wishlistapplication.alarm

import AlarmScheduler
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun AlarmScreen(context: Context,
                modifier: Modifier) {
    val calendar = Calendar.getInstance()
    val scheduler = AlarmScheduler(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val timeState = remember { mutableStateOf("") }

        Button(onClick = {
            calendar.add(Calendar.SECOND, 15) // Schedule alarm in 10 seconds
            scheduler.setAlarm(calendar.timeInMillis)
            timeState.value = "Alarm set for: ${SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(calendar.time)}"
        }) {
            Text(text = "Set Alarm in 10 sec")
        }
        Button(onClick = {
            calendar.add(Calendar.SECOND, 30) // Schedule alarm in 10 seconds
            scheduler.setAlarm(calendar.timeInMillis)
            timeState.value = "Alarm set for: ${SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(calendar.time)}"
        }) {
            Text(text = "Set Alarm in 10 sec")
        }

        Text(text = timeState.value, modifier = Modifier.padding(top = 16.dp))
    }
}
