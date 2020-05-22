package a3labgo.tusar.maheramadan20;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

import a3labgo.tusar.maheramadan20.TimerFragments.AlarmReceiver;
import a3labgo.tusar.maheramadan20.TimerFragments.TimePickerFragment;

import static android.content.Context.ALARM_SERVICE;

public class DialogAlarm extends DialogFragment {

    private TextView setAlarm, cancelAlarm, closeDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_alarm, container, false);

        setAlarm = view.findViewById(R.id.set_time);
        cancelAlarm = view.findViewById(R.id.cancel_alarm);
        closeDialog = view.findViewById(R.id.close_dialog);

//        setting the alarm
        setAlarm.setOnClickListener(v -> {
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(Objects.requireNonNull(getFragmentManager()), "AlarmDialog");
        });

//        canceling the alarm (opening default alarm window
        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), AlarmReceiver.class);
//                SharedPreferences sharedPreferences = getContext().getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
//                intent.putExtra("hourOfDay", sharedPreferences.getInt("hourOfDay", 0));
//                intent.putExtra("minute", sharedPreferences.getInt("minute", 0));
//                System.out.println("hh = " + sharedPreferences.getInt("hourOfDay", 0));
//                System.out.println("hh = " + sharedPreferences.getInt("minute", 0));
////              cancel current alarm
//                AlarmManager alarmManager = (AlarmManager) Objects.requireNonNull(getContext()).getSystemService(ALARM_SERVICE);
//                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                alarmManager.cancel(pendingIntent);

                Intent alarmIntent = new Intent(AlarmClock.ACTION_DISMISS_ALARM);
                startActivity(alarmIntent);
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
