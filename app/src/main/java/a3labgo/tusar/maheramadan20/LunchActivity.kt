package a3labgo.tusar.maheramadan20

import a3labgo.tusar.maheramadan20.databinding.ActivityLuncherBinding
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class LunchActivity : AppCompatActivity() {
    private val splashTimeOut = 2500
    private lateinit var bottomAnimation: Animation
    private lateinit var middleAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLuncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val current_year = Calendar.getInstance().get(Calendar.YEAR).toString();
        val current_date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString();
        val current_month = Calendar.getInstance().get(Calendar.MONTH).toString();

        val predefinedColorArr = arrayOf( Color.RED, Color.GRAY, Color.CYAN, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.WHITE)
        val rand1: Int = (Math.random() * (predefinedColorArr.size - 0)).toInt()
        val rand2: Int = (Math.random() * (predefinedColorArr.size - 0)).toInt()
        val rand3: Int = (Math.random() * (predefinedColorArr.size - 0)).toInt()
        val rand4: Int = (Math.random() * (predefinedColorArr.size - 0)).toInt()


        val wordtoSpan: Spannable = SpannableString(getBanglaYearOrDate(current_year))
        wordtoSpan.setSpan(ForegroundColorSpan(predefinedColorArr[rand1]), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        wordtoSpan.setSpan(ForegroundColorSpan(predefinedColorArr[rand2]), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        wordtoSpan.setSpan(ForegroundColorSpan(predefinedColorArr[rand3]), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        wordtoSpan.setSpan(ForegroundColorSpan(predefinedColorArr[rand4]), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tagLine.setText(wordtoSpan)

        binding.tagLineSub.text = getBanglaYearOrDate(current_date)+" "+getBangleaMonth(current_month)

        middleAnimation = AnimationUtils.loadAnimation(this@LunchActivity, R.anim.middle_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this@LunchActivity, R.anim.bottom_animation)
        binding.tagLine.animation = middleAnimation
        binding.tagLineSub.animation = bottomAnimation

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val i = Intent(this@LunchActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, splashTimeOut.toLong())
    }

    private fun getBanglaYearOrDate(currentYear: String): String {
        var banglaSting = ""
        for (i in currentYear.indices) {
            val temp = "num"+(currentYear.get(i))
//            val bangleChar = string::class.java.getField(temp).getInt(null)
            val bangleChar = getText(applicationContext.resources.getIdentifier(temp, "string", "a3labgo.tusar.maheramadan20"))
            println("" + i + "---" + temp + " " + bangleChar)
            banglaSting += bangleChar.toString()
        }
        return banglaSting
    }

    private fun getBangleaMonth(currentMonth: String): String {
        var banglaSting = ""
        for (i in currentMonth.indices) {
            val temp = "month"+(currentMonth.get(i)+1)
//            val bangleChar = string::class.java.getField(temp).getInt(null)
            val bangleChar = getText(applicationContext.resources.getIdentifier(temp, "string", "a3labgo.tusar.maheramadan20"))
            println("" + i + "---" + temp + " " + bangleChar)
            banglaSting += bangleChar.toString()
        }
        return banglaSting
    }
}