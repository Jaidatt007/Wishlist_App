package com.example.wishlistapplication.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.content.IntentFilter
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.wishlistapplication.R

class AlarmService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    private var audioManager: AudioManager? = null

    // BroadcastReceiver to listen to ringer mode changes
    private val ringerModeReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onReceive(context: Context?, intent: Intent?) {
            val newRingerMode = audioManager?.ringerMode
            handleRingerModeChange(newRingerMode)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (intent?.action == "STOP_ALARM") {
            stopAlarm()  // Stop MediaPlayer properly
            stopVibration()  // Stop vibration
            stopForeground(true) // Remove foreground service
            stopSelf()  // Stop the service
            return START_NOT_STICKY
        }



        // Register receiver for ringer mode changes
        val filter = IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION)
        registerReceiver(ringerModeReceiver, filter, Context.RECEIVER_NOT_EXPORTED)  // Specify the exported flag

        // Check the current ringer mode and start the alarm accordingly
        val ringerMode = audioManager?.ringerMode
        startForeground(1, createNotification())

        handleRingerModeChange(ringerMode)

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleRingerModeChange(ringerMode: Int?) {
        stopAlarm()  // Stop the alarm before checking new mode
        stopVibration()  // Stop vibration before setting new mode

        when (ringerMode) {
            AudioManager.RINGER_MODE_NORMAL -> {
                playAlarmSound()  // Play sound again
                startVibration(longArrayOf(0, 1000, 1000))  // Normal mode with vibration
            }
            AudioManager.RINGER_MODE_VIBRATE -> {
                startVibration(longArrayOf(0, 1000, 1000))  // Vibrate mode only
            }
            AudioManager.RINGER_MODE_SILENT -> {
                // No need to start anything, just ensure everything is stopped
            }
        }
    }


    private fun playAlarmSound() {
        val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mediaPlayer = MediaPlayer.create(this, ringtone)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    private fun stopAlarm() {
        mediaPlayer?.let {
            try {
                if (it.isPlaying) {
                    it.stop()
                }
                it.reset()  // Reset to avoid errors
                it.release()  // Release resources
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        mediaPlayer = null
    }



    // Function to start vibration with specified pattern
    @RequiresApi(Build.VERSION_CODES.O)
    private fun startVibration(vibrationPattern: LongArray, times: String = "infinite") {
        if (vibrator?.hasVibrator() == true) {
            val vibrationEffect = if (times == "once") VibrationEffect.createWaveform(vibrationPattern, -1)
            else VibrationEffect.createWaveform(vibrationPattern, 0) // Repeat indefinitely
            vibrator?.vibrate(vibrationEffect)
        }
    }

    // Function to stop vibration
    private fun stopVibration() {
        vibrator?.cancel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(): Notification {
        val notificationClickIntent = Intent(this, AlarmReceiver::class.java).apply {
            action = "NOTIFICATION_CLICKED"
        }
        val notificationClickPendingIntent = PendingIntent.getBroadcast(this, 3, notificationClickIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val completedIntent = Intent(this, AlarmReceiver::class.java).apply {
            action = "COMPLETED"
        }
        val completedPendingIntent = PendingIntent.getBroadcast(this, 2, completedIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val markAsDoneIntent = Intent(this, AlarmReceiver::class.java).apply {
            action = "MARK_AS_DONE"
        }
        val markAsDonePendingIntent = PendingIntent.getBroadcast(this, 1, markAsDoneIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val stopIntent = Intent(this, AlarmReceiver::class.java).apply {
            action = "STOP_ALARM"
        }
        val stopPendingIntent = PendingIntent.getBroadcast(this, 0, stopIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val channelId = "alarm_channel"
        val notificationManager = getSystemService(NotificationManager::class.java)

        val channel = NotificationChannel(channelId, "Alarm Notifications", NotificationManager.IMPORTANCE_HIGH).apply {
            enableVibration(true)
            vibrationPattern = longArrayOf(1000, 1000, 1000)
        }
        notificationManager.createNotificationChannel(channel)

        // Create notification with actions and delete intent
        return NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Alarm is ringing")
            .setContentText("Swipe to stop")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(notificationClickPendingIntent)
            .addAction(R.drawable.ic_launcher_foreground, "Completed", completedPendingIntent)
            .addAction(R.drawable.ic_launcher_foreground, "Mark as Done", markAsDonePendingIntent)
            .setDeleteIntent(stopPendingIntent) // Stops alarm and vibration when notification is swiped
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        // Unregister receiver and stop the alarm and vibration on service destroy
        unregisterReceiver(ringerModeReceiver)
        stopAlarm()
        stopVibration()
        super.onDestroy()
    }
}
