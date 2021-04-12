package a3labgo.tusar.maheramadan20;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

import a3labgo.tusar.maheramadan20.TimerFragments.AlarmReceiver;
import a3labgo.tusar.maheramadan20.TimerFragments.TimePickerFragment;

import static a3labgo.tusar.maheramadan20.R.string.no_supported_feature;
import static android.content.Context.ALARM_SERVICE;

public class DialogAlarm extends DialogFragment {

    private TextView setAlarm, cancelAlarm, closeDialog;

    //        closing alarm
    Intent alarmClockIntent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER);
    String clockImpls[][] = {
            {"HTC Alarm Clock", "com.htc.android.worldclock", "com.htc.android.worldclock.WorldClockTabControl" },
            {"Standar Alarm Clock", "com.android.deskclock", "com.android.deskclock.AlarmClock"},
            {"Froyo Nexus Alarm Clock", "com.google.android.deskclock", "com.android.deskclock.DeskClock"},
            {"Moto Blur Alarm Clock", "com.motorola.blur.alarmclock",  "com.motorola.blur.alarmclock.AlarmClock"},
            {"Samsung Galaxy Clock", "com.sec.android.app.clockpackage","com.sec.android.app.clockpackage.ClockPackage"} ,
            {"Sony Ericsson Xperia Z", "com.sonyericsson.organizer", "com.sonyericsson.organizer.Organizer_WorldClock" },
            {"ASUS Tablets", "com.asus.deskclock", "com.asus.deskclock.DeskClock"}

    };
    boolean foundClockImpl = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_alarm, container, false);

        setAlarm = view.findViewById(R.id.set_time);
        cancelAlarm = view.findViewById(R.id.cancel_alarm);
        closeDialog = view.findViewById(R.id.close_dialog);

        //        closing alarm
        for(int i=0; i<clockImpls.length; i++) {
            String vendor = clockImpls[i][0];
            String packageName = clockImpls[i][1];
            String className = clockImpls[i][2];
            try {
                ComponentName cn = new ComponentName(packageName, className);
                alarmClockIntent.setComponent(cn);
                System.out.println("Found " + vendor + " --> " + packageName + "/" + className);
                foundClockImpl = true;
            } catch (Exception e) {
                System.out.println(vendor + " does not exists");
            }
        }

//        setting the alarm
        setAlarm.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(Objects.requireNonNull(getFragmentManager()), "AlarmDialog");
        });

//        canceling the alarm (opening default alarm window
        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (foundClockImpl) {
                        Intent alarmIntent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
                        startActivity(alarmIntent);
                    }
                }catch(Exception e){
                    Toast.makeText(getActivity(), getText(no_supported_feature),Toast.LENGTH_SHORT).show();
                }
            }
        });

//        close button click action
        closeDialog.setOnClickListener(v -> Objects.requireNonNull(getDialog()).dismiss());

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

}
