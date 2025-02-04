import com.example.wishlistapplication.alarm.AlarmReceiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlin.random.Random

class AlarmScheduler(private val context: Context) {
    fun setAlarm(timeInMillis: Long) {
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            Random.nextInt(1000, 9999),
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )

        Toast.makeText(context, "Alarm set!", Toast.LENGTH_SHORT).show()
    }
}
