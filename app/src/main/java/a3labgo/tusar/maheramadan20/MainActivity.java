package a3labgo.tusar.maheramadan20;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.AlarmClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import a3labgo.tusar.maheramadan20.TimerFragments.TimePickerFragment;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd,mInterstitialAd1,mInterstitialAd2,mInterstitialAd3;
    TextView arabicYear, englishDate, dayOfWeek, sahriLastTime, fazrTime, iftarTime;
    int startLine,endLine;
    Button buttonSetAlarm,tasbih,doa,karon,sorto,about,onnoelaka;
//    String roza_no[] = {"১","২","৩","৪","৫","৬","৭","৮","৯","১০","১১","১২","১৩","১৪","১৫","১৬","১৭","১৮","১৯","২০","২১","২২","২৩","২৪","২৫","২৬","২৭","২৮","২৯","৩০"};
//    //String day[] = {"বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার"};
//    String day[] = {"শুক্র","শনি","রবি","সোম","মঙ্গল","বুধ","বৃহস্পতি","শুক্র","শনি","রবি","সোম","মঙ্গল","বুধ","বৃহস্পতি","শুক্র","শনি","রবি","সোম","মঙ্গল","বুধ","বৃহস্পতি","শুক্র","শনি","রবি","সোম","মঙ্গল","বুধ","বৃহস্পতি","শুক্র","শনি"};
//    String date[] = {"১৮ মে","১৯ মে","২০ মে","২১ মে","২২ মে","২৩ মে","২৪ মে","২৫ মে","২৬ মে","২৭ মে","২৮ মে","২৯ মে","৩০ মে","৩১ মে","১ জুন","২ জুন","৩ জুন","৪ জুন","৫ জুন","৬ জুন","৭ জুন","৮ জুন","৯ জুন","১০ জুন","১১ জুন","১২ জুন","১৩ জুন","১৪ জুন","১৫ জুন","১৬ জুন"};
////    String sahri[] = {"৩:৪৬","৩:৪৫","৩:৪৫","৩:৪৪","৩:৪৩","৩:৪৩","৩:৪২","৩:৪২","৩:৪২","৩:৪২","৩:৪১","৩:৪১","৩:৪০","৩:৪০","৩:৩৯","৩:৩৯","৩:৩৯","৩:৩৯","৩:৩৯","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮"};
////    String fazar[] = {"৩:৫২","৩:৫১","৩:৫০","৩:৫০","৩:৪৯","৩:৪৯","৩:৪৮","৩:৪৮","৩:৪৮","৩:৪৭","৩:৪৭","৩:৪৭","৩:৪৬","৩:৪৬","৩:৪৫","৩:৪৫","৩:৪৫","৩:৪৫","৩:৪৫","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪"};
////    String iftar[] = {"৬:৩৯","৬:৩৯","৬:৪০","৬:৪০","৬:৪১","৬:৪১","৬:৪২","৬:৪২","৬:৪৩","৬:৪৩","৬:৪৪","৬:৪৪","৬:৪৫","৬:৪৫","৬:৪৫","৬:৪৬","৬:৪৬","৬:৪৬","৬:৪৭","৬:৪৭","৬:৪৭","৬:৪৮","৬:৪৮","৬:৪৯","৬:৪৯","৬:৫০","৬:৫০","৬:৫০","৬:৫১","৬:৫১"};
//    String sahri[] = {"৩:৪৬","৩:৪৫","৩:৪৪","৩:৪৪","৩:৪৩","৩:৪৩","৩:৪২","৩:৪২","৩:৪১","৩:৪১","৩:৪০","৩:৪০","৩:৪০","৩:৩৯","৩:৩৯","৩:৩৯","৩:৩৯","৩:৩৯","৩:৩৯","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৮","৩:৩৭","৩:৩৮","৩:৩৮","৩:৩৮"};
//    String fazar[] = {"৩:৫২","৩:৫১","৩:৫০","৩:৫০","৩:৪৯","৩:৪৯","৩:৪৮","৩:৪৮","৩:৪৭","৩:৪৭","৩:৪৬","৩:৪৬","৩:৪৬","৩:৪৫","৩:৪৫","৩:৪৫","৩:৪৫","৩:৪৫","৩:৪৫","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৪","৩:৪৩","৩:৪৪","৩:৪৪","৩:৪৪"};
//    String iftar[] = {"৬:৩৯","৬:৩৯","৬:৪০","৬:৪০","৬:৪১","৬:৪২","৬:৪২","৬:৪২","৬:৪৩","৬:৪৩","৬:৪৩","৬:৪৪","৬:৪৫","৬:৪৫","৬:৪৬","৬:৪৬","৬:৪৬","৬:৪৭","৬:৪৭","৬:৪৭","৬:৪৮","৬:৪৮","৬:৪৯","৬:৪৯","৬:৫০","৬:৫০","৬:৫০","৬:৫০","৬:৫১","৬:৫১"};

    String roza_no[] = {"১","২","৩","৪","৫","৬","৭","৮","৯","১০","১১","১২","১৩","১৪","১৫","১৬","১৭","১৮","১৯","২০","২১","২২","২৩","২৪","২৫","২৬","২৭","২৮","২৯","৩০"};
    String day[] = {"শনি","রবি","সোম","মঙ্গল","বুধ","বৃহস্পতি","শুক্র"};
    String date[] = {"২৫ এপ্রিল ","২৬ এপ্রিল ","২৭ এপ্রিল ","২৮ এপ্রিল ","২৯ এপ্রিল ","৩০ এপ্রিল ","১ মে","২ মে","৩ মে","৪ মে","৫ মে","৬ মে","৭ মে","৮ মে","৯ মে","১০ মে","১১ মে","১২ মে","১৩ মে","১৪ মে","১৫ মে","১৬ মে","১৭ মে","১৮ মে","১৯ মে","২০ মে","২১ মে","২২ মে","২৩ মে","২৪ মে"};
    String sahri[] = {"৪:০৫","৪:০৪","৪:০৩","৪:০২","৪:০১","৪:০০","৩:৫৯","৩:৫৮","৩:৫৭","৩:৫৫","৩:৫৪","৩:৫৩","৩:৫২","৩:৫১","৩:৫০","৩:৫০","৩:৪৯","৩:৪৯","৩:৪৮","৩:৪৮","৩:৪৭","৩:৪৭","৩:৪৬","৩:৪৬","৩:৪৫","৩:৪৪","৩:৪৪","৩:৪৩","৩:৪৩","৩:৪২"};
    String fazar[] = {"৪:১১","৪:১০","৪:০৯","৪:০৮","৪:০৭","৪:০৬","৪:০৫","৪:০৪","৪:০৩","৪:০০","৪:০০","৩:৫৯","৩:৫৮","৩:৫৭","৩:৫৬","৩:৫৬","৩:৫৫","৩:৫৫","৩:৫৪","৩:৫৪","৩:৫৩","৩:৫৩","৩:৫২","৩:৫২","৩:৫১","৩:৫০","৩:৫০","৩:৪৯","৩:৪৯","৩:৪৮"};
    String iftar[] = {"৬:২৮","৬:২৯","৬:২৯","৬:২৯","৬:৩০","৬:৩০","৬:৩১","৬:৩১","৬:৩২","৬:৩২","৬:৩৩","৬:৩৩","৬:৩৪","৬:৩৪","৬:৩৫","৬:৩৫","৬:৩৬","৬:৩৬","৬:৩৬","৬:৩৭","৬:৩৭","৬:৩৮","৬:৩৮","৬:৩৯","৬:৩৯","৬:৪০","৬:৪০","৬:৪১","৬:৪২","৬:৪২"};
    int systemDate[] = new int[2];
    RecyclerView recyclerView;
    AdapterMainTable adapter;

//    Alarm
    private Button mOpenAlarmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arabicYear = findViewById(R.id.tv1);
        englishDate = findViewById(R.id.tv2);
        dayOfWeek = findViewById(R.id.tv3);
        sahriLastTime = findViewById(R.id.tv4);
        fazrTime = findViewById(R.id.tv5);
        iftarTime = findViewById(R.id.tv6);
        buttonSetAlarm = findViewById(R.id.setAlarm);
        karon = findViewById(R.id.karonBtn);
        sorto = findViewById(R.id.sortoBtn);
        doa = findViewById(R.id.doaBtn);
        tasbih = findViewById(R.id.tasbihBtn);
        about = findViewById(R.id.About);
        onnoelaka = findViewById(R.id.onnoElaka);



        arabicYear.setText(R.string.arabic_year);
        englishDate.setText(R.string.english_year);
        dayOfWeek.setText(R.string.day_of_week);
        sahriLastTime.setText(R.string.last_time_of_suhoor);
        fazrTime.setText(R.string.starting_time_fazr);
        iftarTime.setText(R.string.Iftaar_time);

        MobileAds.initialize(this, initializationStatus -> {});
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ///////////////////////////////////////////ad initialize/////////////////////////////////

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd1 = new InterstitialAd(this);
        mInterstitialAd2= new InterstitialAd(this);
        mInterstitialAd3= new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9447458149465385/9820148847");                 //for release
        mInterstitialAd1.setAdUnitId("ca-app-pub-9447458149465385/3820597142");                 //for release
        mInterstitialAd2.setAdUnitId("ca-app-pub-9447458149465385/4978885708");                 //for release
        mInterstitialAd3.setAdUnitId("ca-app-pub-9447458149465385/1039640693");                 //for release
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd1.loadAd(new AdRequest.Builder().build());
        mInterstitialAd2.loadAd(new AdRequest.Builder().build());
        mInterstitialAd3.loadAd(new AdRequest.Builder().build());
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");               //for test
        //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());


        mInterstitialAd.setAdListener(new AdListener()
        {
            public void onAdClosed(){
                Intent intent2 = new Intent(getApplicationContext(),OtherAreaActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        mInterstitialAd1.setAdListener(new AdListener()
        {
            public void onAdClosed(){
                Intent intent2 = new Intent(getApplicationContext(),DoaActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd1.loadAd(new AdRequest.Builder().build());
            }
        });
        mInterstitialAd2.setAdListener(new AdListener()
        {
            public void onAdClosed(){
                Intent intent2 = new Intent(getApplicationContext(),KaronActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd2.loadAd(new AdRequest.Builder().build());
            }
        });
        mInterstitialAd3.setAdListener(new AdListener()
        {
            public void onAdClosed(){
                Intent intent2 = new Intent(getApplicationContext(),SortoActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd3.loadAd(new AdRequest.Builder().build());
            }
        });

        onnoelaka.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(),OtherAreaActivity.class);
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                startActivity(intent2);
            }

        });

        doa.setOnClickListener(view -> {
            if(mInterstitialAd1.isLoaded()){
                mInterstitialAd1.show();
            }
            else {
                Intent intent2 = new Intent(getApplicationContext(),DoaActivity.class);
                startActivity(intent2);
            }
        });

        karon.setOnClickListener(view -> {
            if(mInterstitialAd2.isLoaded()){
                mInterstitialAd2.show();
            }
            else {
                Intent intent3 = new Intent(getApplicationContext(),KaronActivity.class);
                startActivity(intent3);
            }
        });

        tasbih.setOnClickListener(view -> {
            String title = getString(R.string.tasbih);
            String message = "তাসবিহ অ্যাপটি ডাউনলোড করতে আপনি কি Google PlayStore - এ যেতে চান?";
            String buttonName = "tasbih";
            createDialog(title, message, buttonName);
        });

        sorto.setOnClickListener(view -> {
            if(mInterstitialAd3.isLoaded()){
                mInterstitialAd3.show();
            }
            else {
                Intent intent4 = new Intent(getApplicationContext(),SortoActivity.class);
                startActivity(intent4);
            }
        });

        buttonSetAlarm.setOnClickListener(v -> {
            DialogAlarm dialogAlarm = new DialogAlarm();
            dialogAlarm.show(MainActivity.this.getSupportFragmentManager(), "AlarmDialog");
        });

        about.setOnClickListener(view -> {
            String title = "প্রস্তুতকারক";
            String message = "আল-আমিন তুষার এবং জাহিদুল ইসলাম \n" +
                                "কম্পিউটার বিজ্ঞান ও প্রকৌশল বিভাগ \n" +
                                "জাহাঙ্গীরনগর বিশ্ববিদ্যালয় \n" +
                                "প্রতিষ্ঠানঃ 3LabGo";
            createDialog(title, message, "contributors");
        });

    }

    private void createDialog(String title, String message, String buttonName) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setIcon(R.drawable.dev);
        alertDialog.setMessage(message);
        if(buttonName.equals("contributors")) {
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                    (dialog, which) -> dialog.dismiss());
        }
        else if(buttonName.equals("tasbih")) {
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ঠিক আছে", (dialog, which) -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.alasoft.tk.counter"));
                startActivity(intent);
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "না", (dialog, which) -> {
                dialog.cancel();
            });
        }
        alertDialog.show();
    }


    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerviewMainPage);
        adapter = new AdapterMainTable(roza_no, day, date, sahri, fazar, iftar, systemDate, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Calendar calendar = Calendar.getInstance();
        systemDate[0] = calendar.get(Calendar.DAY_OF_MONTH);
        systemDate[1] = calendar.get(Calendar.MONTH);
        initRecyclerView();
    }

}