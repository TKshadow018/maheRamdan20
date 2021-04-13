package a3labgo.tusar.maheramadan20
import a3labgo.tusar.maheramadan20.databinding.ActivityMainBinding
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private var mInterstitialAd1: InterstitialAd? = null
    private var mInterstitialAd2: InterstitialAd? = null
    private var mInterstitialAd3: InterstitialAd? = null
    //    var arabicYear: TextView? = null
//    var englishDate: TextView? = null
//    var dayOfWeek: TextView? = null
//    var sahriLastTime: TextView? = null
//    var fazrTime: TextView? = null
//    var iftarTime: TextView? = null
//    var buttonSetAlarm: Button? = null
//    var tasbih: Button? = null
//    var doa: Button? = null
//    var karon: Button? = null
//    var sorto: Button? = null
//    var about: Button? = null
//    var onnoelaka: Button? = null
//    var mAdView: AdView? = null
    var roza_no: ArrayList<String>? = null
    var day: ArrayList<String>? = null
    var date: ArrayList<String>? = null
    var sahri: ArrayList<String>? = null
    var fazar: ArrayList<String>? = null
    var iftar: ArrayList<String>? = null
    var numberList = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
    var recyclerView: RecyclerView? = null
    var adapter: AdapterMainTable? = null
    var titleHere: TextView? = null
    private val mOpenAlarmDialog: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        Thread {
            if (checkVersion()) {
                Thread {
                    saveDataInFileFromWeb("dates.txt")
                    saveDataInFileFromWeb("fazar.txt")
                    saveDataInFileFromWeb("iftar.txt")
                    saveDataInFileFromWeb("sehri.txt")
                }.start()
            }
        }.start()
        ////////////////////////////year////////////////////////////
        val calendar = Calendar.getInstance()
        val currentYear = calendar[Calendar.YEAR].toString()
        val CurrentYearHijri = (calendar[Calendar.YEAR] - 579).toString()
        val yearString: String
        val yearChar = CharArray(4)
        val yearCharHijri = CharArray(4)
        var i = 0
        for (ch in currentYear.toCharArray()) {
            yearChar[i] = numberList[Character.getNumericValue(ch)]
            i++
        }
        i = 0
        for (ch in CurrentYearHijri.toCharArray()) {
            yearCharHijri[i] = numberList[Character.getNumericValue(ch)]
            i++
        }
        yearString = String(yearChar)
        //        yearStringHijri = new String(yearCharHijri);
        title = getText(R.string.app_name).toString() + " " + yearString


        ///////////////////////////////////////Data List////////////////////////////////////
        date = ArrayList()
        roza_no = ArrayList()
        sahri = ArrayList()
        fazar = ArrayList()
        iftar = ArrayList()
        day = ArrayList()
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

//        List<String> testDevices = new ArrayList<>();
//        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);
//        System.out.println(testDevices);
//        MobileAds.initialize(this, initializationStatus -> {});
//        RequestConfiguration requestConfiguration
//                = new RequestConfiguration.Builder()
//                .setTestDeviceIds(testDevices)
//                .build();
//        MobileAds.setRequestConfiguration(requestConfiguration);
        var adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task: Task<InstanceIdResult> ->
                    if (!task.isSuccessful) {
                        return@addOnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result.token
                    // Log and toast
                    val msg = getString(R.string.msg_token_fmt, token)
                }
        binding.titleHere.setText(getText(R.string.main_title).toString() + " " + yearString)
        binding.tv1.setText(R.string.roza_no)
        binding.tv2.setText(R.string.english_date)
        binding.tv3.setText(R.string.day_of_week)
        binding.tv4.setText(R.string.last_time_of_suhoor)
        binding.tv5.setText(R.string.starting_time_fazr)
        binding.tv6.setText(R.string.Iftaar_time)
        if (!readFomSavedFiles()) {
            addAllData()
        }
        adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        ///////////////////////////////////////////ad initialize/////////////////////////////////

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        //9820148847
        InterstitialAd.load(this, "ca-app-pub-9447458149465385/9820148847", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
                mInterstitialAd?.setFullScreenContentCallback(object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        val intent = Intent(applicationContext, OtherAreaActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onAdShowedFullScreenContent() {
                        mInterstitialAd = null
                    }
                })
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                mInterstitialAd = null
            }
        })
        InterstitialAd.load(this, "ca-app-pub-9447458149465385/10331737123820597142", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd1 = interstitialAd
                mInterstitialAd1?.setFullScreenContentCallback(object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        val intent = Intent(applicationContext, DoaActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onAdShowedFullScreenContent() {
                        mInterstitialAd1 = null
                    }
                })
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                mInterstitialAd1 = null
            }
        })
        InterstitialAd.load(this, "ca-app-pub-9447458149465385/4978885708", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd2 = interstitialAd
                mInterstitialAd2?.setFullScreenContentCallback(object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        val intent = Intent(applicationContext, KaronActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onAdShowedFullScreenContent() {
                        mInterstitialAd2 = null
                    }
                })
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                mInterstitialAd2 = null
            }
        })
        InterstitialAd.load(this, "ca-app-pub-9447458149465385/1039640693", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd3 = interstitialAd
                mInterstitialAd3?.setFullScreenContentCallback(object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        val intent = Intent(applicationContext, SortoActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onAdShowedFullScreenContent() {
                        mInterstitialAd3 = null
                    }
                })
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                mInterstitialAd3 = null
            }
        })
        binding.onnoElaka.setOnClickListener(View.OnClickListener { view: View? ->
            val intent2 = Intent(applicationContext, OtherAreaActivity::class.java)
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                startActivity(intent2)
            }
        })
        binding.doaBtn.setOnClickListener(View.OnClickListener { view: View? ->
            if (mInterstitialAd1 != null) {
                mInterstitialAd1?.show(this)
            } else {
                val intent2 = Intent(applicationContext, DoaActivity::class.java)
                startActivity(intent2)
            }
        })
        binding.karonBtn.setOnClickListener(View.OnClickListener { view: View? ->
            if (mInterstitialAd2 != null) {
                mInterstitialAd2?.show(this)
            } else {
                val intent3 = Intent(applicationContext, KaronActivity::class.java)
                startActivity(intent3)
            }
        })
        binding.tasbihBtn.setOnClickListener(View.OnClickListener { view: View? ->
            val title = getString(R.string.tasbih)
            val message = "তাসবিহ অ্যাপটি ডাউনলোড করতে আপনি কি Google PlayStore - এ যেতে চান?"
            val buttonName = "tasbih"
            createDialog(title, message, buttonName)
        })
        binding.sortoBtn.setOnClickListener(View.OnClickListener { view: View? ->
            if (mInterstitialAd3 != null) {
                mInterstitialAd3?.show(this)
            } else {
                val intent4 = Intent(applicationContext, SortoActivity::class.java)
                startActivity(intent4)
            }
        })
        binding.setAlarm.setOnClickListener { v: View? ->
            val dialogAlarm = DialogAlarm()
            dialogAlarm.show(this@MainActivity.supportFragmentManager, "AlarmDialog")
        }
        binding.about.setOnClickListener{
            val title = "প্রস্তুতকারক"
            val message = """
                আল-আমিন তুষার, জাহিদুল ইসলাম এবং মোঃ আলী ফাইয়াদ 
                কম্পিউটার বিজ্ঞান ও প্রকৌশল বিভাগ 
                জাহাঙ্গীরনগর বিশ্ববিদ্যালয় 
                প্রতিষ্ঠানঃ 3LabGo
                """.trimIndent()
            createDialog(title, message, "contributors")
        }

        // ATTENTION: This was auto-generated to handle app links.(App Indexing)
        val appLinkIntent = intent
        val appLinkAction = appLinkIntent.action
        val appLinkData = appLinkIntent.data

        adapter = AdapterMainTable(this)
        binding.recyclerviewMainPage.adapter = adapter
        binding.recyclerviewMainPage.layoutManager = LinearLayoutManager(this)
    }

    override fun onRestart() {
        super.onRestart()
        if (!(date?.size == 29 || date?.size == 30) && (sahri?.size == 29 || sahri?.size == 30) && (fazar?.size == 29 || fazar?.size == 30) && (iftar?.size == 29 || iftar?.size == 30)) {
            addAllData()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter?.setData(roza_no!!, day!!, date!!, sahri!!, fazar!!, iftar!!)
    }
    private fun readFomSavedFiles(): Boolean {
        // set up variables
        val dateFile = "dates.txt"
        val sahriFile = "sehri.txt"
        val fazarFile = "fazar.txt"
        val iftarFile = "iftar.txt"
        var line: String
        return try {
            val file = openFileInput(dateFile)
            val file1 = openFileInput(sahriFile)
            val file2 = openFileInput(fazarFile)
            val file3 = openFileInput(iftarFile)
            val input = BufferedReader(InputStreamReader(file))
            val input1 = BufferedReader(InputStreamReader(file1))
            val input2 = BufferedReader(InputStreamReader(file2))
            val input3 = BufferedReader(InputStreamReader(file3))
            if (!input.ready() || !input2.ready() || !input3.ready() || !input1.ready()) {
                throw IOException()
            } else {
                date?.clear()
                sahri?.clear()
                fazar?.clear()
                iftar?.clear()
                while (input.readLine().also { line = it } != null) {
                    date?.add(line)
                }
                while (input1.readLine().also { line = it } != null) {
                    sahri?.add(line)
                }
                while (input2.readLine().also { line = it } != null) {
                    fazar?.add(line)
                }
                while (input3.readLine().also { line = it } != null) {
                    iftar?.add(line)
                }
                addfixedData()
            }
            input.close()
            input1.close()
            input2.close()
            input3.close()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    private fun addAllData() {
        date?.clear()
        date?.add("১৪ এপ্রিল")
        date?.add("১৫ এপ্রিল")
        date?.add("১৬ এপ্রিল")
        date?.add("১৭ এপ্রিল")
        date?.add("১৮ এপ্রিল")
        date?.add("১৯ এপ্রিল")
        date?.add("২০ এপ্রিল")
        date?.add("২১ এপ্রিল")
        date?.add("২২ এপ্রিল")
        date?.add("২৩ এপ্রিল")
        date?.add("২৪ এপ্রিল")
        date?.add("২৫ এপ্রিল")
        date?.add("২৬ এপ্রিল")
        date?.add("২৭ এপ্রিল")
        date?.add("২৮ এপ্রিল")
        date?.add("২৯ এপ্রিল")
        date?.add("৩০ এপ্রিল")
        date?.add("১ মে")
        date?.add("২ মে")
        date?.add("৩ মে")
        date?.add("৪ মে")
        date?.add("৫ মে")
        date?.add("৬ মে")
        date?.add("৭ মে")
        date?.add("৮ মে")
        date?.add("৯ মে")
        date?.add("১০ মে")
        date?.add("১১ মে")
        date?.add("১২ মে")
        date?.add("১৩ মে")
        sahri?.clear()
        sahri?.add("৪:২০")
        sahri?.add("৪:১৯")
        sahri?.add("৪:১৮")
        sahri?.add("৪:১৭")
        sahri?.add("৪:১৬")
        sahri?.add("৪:১৫")
        sahri?.add("৪:১৪")
        sahri?.add("৪:১৩")
        sahri?.add("৪:১২")
        sahri?.add("৪:১১")
        sahri?.add("৪:১০")
        sahri?.add("৪:০৯")
        sahri?.add("৪:০৮")
        sahri?.add("৪:০৭")
        sahri?.add("৪:০৬")
        sahri?.add("৪:০৫")
        sahri?.add("৪:০৪")
        sahri?.add("৪:০৩")
        sahri?.add("৪:০২")
        sahri?.add("৪:০১")
        sahri?.add("৪:০১")
        sahri?.add("৪:০০")
        sahri?.add("৩:৫৯")
        sahri?.add("৩:৫৮")
        sahri?.add("৩:৫৭")
        sahri?.add("৩:৫৭")
        sahri?.add("৩:৫৬")
        sahri?.add("৩:৫৫")
        sahri?.add("৩:৫৪")
        sahri?.add("৩:৫৪")
        fazar?.clear()
        fazar?.add("৪:২৬")
        fazar?.add("৪:২৫")
        fazar?.add("৪:২৪")
        fazar?.add("৪:২৩")
        fazar?.add("৪:২২")
        fazar?.add("৪:২১")
        fazar?.add("৪:২০")
        fazar?.add("৪:১৯")
        fazar?.add("৪:১৮")
        fazar?.add("৪:১৭")
        fazar?.add("৪:১৬")
        fazar?.add("৪:১৫")
        fazar?.add("৪:১৪")
        fazar?.add("৪:১৩")
        fazar?.add("৪:১২")
        fazar?.add("৪:১১")
        fazar?.add("৪:১০")
        fazar?.add("৪:০৯")
        fazar?.add("৪:০৮")
        fazar?.add("৪:০৭")
        fazar?.add("৪:০৬")
        fazar?.add("৪:০৫")
        fazar?.add("৪:০৪")
        fazar?.add("৪:০৩")
        fazar?.add("৪:০২")
        fazar?.add("৪:০১")
        fazar?.add("৪:০১")
        fazar?.add("৪:০০")
        fazar?.add("৩:৫৯")
        fazar?.add("৩:৫৮")
        iftar?.clear()
        iftar?.add("৬:২০")
        iftar?.add("৬:২০")
        iftar?.add("৬:২১")
        iftar?.add("৬:২১")
        iftar?.add("৬:২২")
        iftar?.add("৬:২২")
        iftar?.add("৬:২৩")
        iftar?.add("৬:২৩")
        iftar?.add("৬:২৩")
        iftar?.add("৬:২৪")
        iftar?.add("৬:২৪")
        iftar?.add("৬:২৫")
        iftar?.add("৬:২৫")
        iftar?.add("৬:২৬")
        iftar?.add("৬:২৬")
        iftar?.add("৬:২৭")
        iftar?.add("৬:২৭")
        iftar?.add("৬:২৮")
        iftar?.add("৬:২৮")
        iftar?.add("৬:২৮")
        iftar?.add("৬:২৯")
        iftar?.add("৬:২৯")
        iftar?.add("৬:৩০")
        iftar?.add("৬:৩০")
        iftar?.add("৬:৩১")
        iftar?.add("৬:৩২")
        iftar?.add("৬:৩২")
        iftar?.add("৬:৩২")
        iftar?.add("৬:৩৩")
        iftar?.add("৬:৩৩")
        addfixedData()
    }

    private fun addfixedData() {
        roza_no?.clear()
        roza_no?.add("১")
        roza_no?.add("২")
        roza_no?.add("৩")
        roza_no?.add("৪")
        roza_no?.add("৫")
        roza_no?.add("৬")
        roza_no?.add("৭")
        roza_no?.add("৮")
        roza_no?.add("৯")
        roza_no?.add("১০")
        roza_no?.add("১১")
        roza_no?.add("১২")
        roza_no?.add("১৩")
        roza_no?.add("১৪")
        roza_no?.add("১৫")
        roza_no?.add("১৬")
        roza_no?.add("১৭")
        roza_no?.add("১৮")
        roza_no?.add("১৯")
        roza_no?.add("২০")
        roza_no?.add("২১")
        roza_no?.add("২২")
        roza_no?.add("২৩")
        roza_no?.add("২৪")
        roza_no?.add("২৫")
        roza_no?.add("২৬")
        roza_no?.add("২৭")
        roza_no?.add("২৮")
        roza_no?.add("২৯")
        roza_no?.add("৩০")
        day?.clear()
        day?.add("বুধ")
        day?.add("বৃহস্পতি")
        day?.add("শুক্র")
        day?.add("শনি")
        day?.add("রবি")
        day?.add("সোম")
        day?.add("মঙ্গল")
    }

    private fun createDialog(title: String, message: String, buttonName: String) {
        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
        alertDialog.setTitle(title)
        alertDialog.setIcon(R.drawable.dev)
        alertDialog.setMessage(message)
        if (buttonName == "contributors") {
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে"
            ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
        } else if (buttonName == "tasbih") {
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ঠিক আছে") { dialog: DialogInterface?, which: Int ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("market://details?id=com.alasoft.tk.counter")
                startActivity(intent)
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "না") { dialog: DialogInterface, which: Int -> dialog.cancel() }
        }
        alertDialog.show()
    }

    private fun saveDataInFileFromWeb(filename: String) {
        val outputStream: FileOutputStream
        var line: String
        var url: URL? = null
        try {
            url = URL("https://tusar.000webhostapp.com/ramdan/$filename")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            outputStream = openFileOutput(filename, MODE_PRIVATE)
            val input = BufferedReader(InputStreamReader(Objects.requireNonNull(url)?.openStream()))
            var index = 0
            input.forEachLine {
                if (index == 0) {
                    outputStream.write(it.toByteArray())
                } else {
                    outputStream.write(("\n"+it).toByteArray())
                }
                index++
            }
//            while (input.readLine().also { line = it}) {
//                if (index == 0) {
//                    outputStream.write(line.toByteArray())
//                } else {
//                    outputStream.write(("\n"+line).toByteArray())
//                }
//                index++
//            }
//            input.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun checkVersion(): Boolean {
        val outputStream: FileOutputStream
        var line: String
        //Read from Devie
        val input: BufferedReader
        var currentVersion = 0
        try {
            val file4 = openFileInput("version.txt")
            val input4 = BufferedReader(InputStreamReader(file4))
            if (!input4.ready()) {
                throw IOException()
            } else {
                input4.forEachLine {
                    currentVersion = it.toInt()
                }
//                while (input4.readLine().also { line? = it } != null) {
//                    currentVersion = line.toInt()
//                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //Read from Online
        val url: URL
        url = try {
            URL("https://tusar.000webhostapp.com/ramdan/version.txt")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            return false
        }
        try {
            input = BufferedReader(InputStreamReader(Objects.requireNonNull(url).openStream()))
            while (input.readLine().also { line = it } != null) {
                // do something with line //read here
                val onlineVersion = line.toInt()
                return if (currentVersion < onlineVersion) {
                    outputStream = openFileOutput("version.txt", MODE_PRIVATE)
                    outputStream.write(line.toByteArray())
                    true
                } else {
                    false
                }
            }
            input.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

//    private fun initRecyclerView() {
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        adapter = AdapterMainTable(roza_no!!, day!!, date!!, sahri!!, fazar!!, iftar!!, systemDate, this)
//        binding.recyclerviewMainPage.adapter = adapter
//        binding.recyclerviewMainPage.layoutManager = LinearLayoutManager(this)
//        adapter?.notifyDataSetChanged()
//    }
}