package a3labgo.tusar.maheramadan20.timerFragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val hourOfDay = intent.getIntExtra("hourOfDay", 0)
        val minute = intent.getIntExtra("minute", 0)
        val alarmIntent = Intent(AlarmClock.ACTION_SET_ALARM)
        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hourOfDay)
        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, minute)
        alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.applicationContext.startActivity(alarmIntent)
    }
}