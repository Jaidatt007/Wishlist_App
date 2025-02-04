package com.example.wishlistapplication.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return

        if(intent.action == "NOTIFICATION_CLICKED"){
            Toast.makeText(context,"NOTIFICATION_CLICKED",Toast.LENGTH_SHORT).show()
        }else if (intent.action == "STOP_ALARM") {
            context.stopService(Intent(context, AlarmService::class.java))
        }else if(intent.action == "MARK_AS_DONE" ){
            Toast.makeText(context,"MARK_AS_DONE",Toast.LENGTH_SHORT).show()
        }else if(intent.action == "COMPLETED" ){
            Toast.makeText(context,"COMPLETED",Toast.LENGTH_SHORT).show()
        } else {
            val serviceIntent = Intent(context, AlarmService::class.java)
            ContextCompat.startForegroundService(context, serviceIntent)
        }
    }
}

