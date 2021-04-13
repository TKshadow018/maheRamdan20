package a3labgo.tusar.maheramadan20

import a3labgo.tusar.maheramadan20.R.string
import a3labgo.tusar.maheramadan20.timerFragments.TimePickerFragment
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogAlarm : DialogFragment() {
    //        closing alarm
    var alarmClockIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
    var clockImpls = arrayOf(arrayOf("HTC Alarm Clock", "com.htc.android.worldclock", "com.htc.android.worldclock.WorldClockTabControl"), arrayOf("Standar Alarm Clock", "com.android.deskclock", "com.android.deskclock.AlarmClock"), arrayOf("Froyo Nexus Alarm Clock", "com.google.android.deskclock", "com.android.deskclock.DeskClock"), arrayOf("Moto Blur Alarm Clock", "com.motorola.blur.alarmclock", "com.motorola.blur.alarmclock.AlarmClock"), arrayOf("Samsung Galaxy Clock", "com.sec.android.app.clockpackage", "com.sec.android.app.clockpackage.ClockPackage"), arrayOf("Sony Ericsson Xperia Z", "com.sonyericsson.organizer", "com.sonyericsson.organizer.Organizer_WorldClock"), arrayOf("ASUS Tablets", "com.asus.deskclock", "com.asus.deskclock.DeskClock"))
    var foundClockImpl = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialog = super.onCreateDialog(savedInstanceState)
        Objects.requireNonNull(dialog.window)?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.dialog_alarm, container, false)
        val setAlarm = view.findViewById<TextView>(R.id.set_time)
        val cancelAlarm = view.findViewById<TextView>(R.id.cancel_alarm)
        val closeDialog = view.findViewById<TextView>(R.id.close_dialog)

        //        closing alarm
        for (clockImpl in clockImpls) {
            try {
                val cn = ComponentName(clockImpl[1], clockImpl[2])
                alarmClockIntent.component = cn
                foundClockImpl = true
            } catch (ignored: Exception) {
            }
        }

//        setting the alarm
        setAlarm.setOnClickListener {
            val timePicker: DialogFragment = TimePickerFragment()
            timePicker.show(Objects.requireNonNull(fragmentManager)!!, "AlarmDialog")
        }

//        canceling the alarm (opening default alarm window
        cancelAlarm.setOnClickListener {
            try {
                if (foundClockImpl) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        startActivity(Intent(AlarmClock.ACTION_DISMISS_ALARM))
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(activity, getText(string.no_supported_feature), Toast.LENGTH_SHORT).show()
            }
        }

//        close button click action
        closeDialog.setOnClickListener { Objects.requireNonNull(getDialog())?.dismiss() }
        return view
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            Objects.requireNonNull(dialog.window)?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}