package a3labgo.tusar.maheramadan20.timerFragments

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment(), OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c[Calendar.HOUR_OF_DAY]
        val minute = c[Calendar.MINUTE]
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {

//        perfectly working code
        val alarmManager = Objects.requireNonNull(context)?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("hourOfDay", hourOfDay)
        intent.putExtra("minute", minute)
        //        SharedPreferences sharedPreferences = getContext().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("hourOfDay", hourOfDay);
//        editor.putInt("minute", minute);
//        editor.commit();
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager[AlarmManager.RTC, System.currentTimeMillis()] = pendingIntent

//        if(alarmManager != null) {
//            System.out.println("if");
//            alarmManager.cancel(pendingIntent);
//            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + AlarmManager.INTERVAL_DAY /* control here for when to set the intent */, pendingIntent);
//        }
//        else {
//            System.out.println("else");
//            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() /* control here for when to set the intent */, pendingIntent);
//        }
    }
}