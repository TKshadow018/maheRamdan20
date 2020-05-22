package a3labgo.tusar.maheramadan20.TimerFragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int hourOfDay = intent.getIntExtra("hourOfDay", 0);
        int minute = intent.getIntExtra("minute", 0);

        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hourOfDay);
        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
        alarmIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        context.getApplicationContext().startActivity(alarmIntent);
    }
}
