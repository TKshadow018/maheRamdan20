package a3labgo.tusar.maheramadan20.TimerFragments;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
import java.util.Objects;

import a3labgo.tusar.maheramadan20.MainActivity;
import a3labgo.tusar.maheramadan20.R;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

//        perfectly working code
        AlarmManager alarmManager = (AlarmManager) Objects.requireNonNull(getContext()).getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        intent.putExtra("hourOfDay", hourOfDay);
        intent.putExtra("minute", minute);
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("hourOfDay", hourOfDay);
//        editor.putInt("minute", minute);
//        editor.commit();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()/* control here for when to set the intent */, pendingIntent);

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