package a3labgo.tusar.maheramadan20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.AlarmClock;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd,mInterstitialAd1,mInterstitialAd2,mInterstitialAd3;
    TextView[] tv;
    TextView title;
    int total_cell=186;
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
    String day[] = {"শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার","সোমবার","মঙ্গলবার","বুধবার","বৃহস্পতিবার","শুক্রবার","শনিবার","রবিবার"};
    String date[] = {"২৫ এপ্রিল ","২৬ এপ্রিল ","২৭ এপ্রিল ","২৮ এপ্রিল ","২৯ এপ্রিল ","৩০ এপ্রিল ","১ মে","২ মে","৩ মে","৪ মে","৫ মে","৬ মে","৭ মে","৮ মে","৯ মে","১০ মে","১১ মে","১২ মে","১৩ মে","১৪ মে","১৫ মে","১৬ মে","১৭ মে","১৮ মে","১৯ মে","২০ মে","২১ মে","২২ মে","২৩ মে","২৪ মে"};
    String sahri[] = {"৪:০৫","৪:০৪","৪:০৩","৪:০২","৪:০১","৪:০০","৩:৫৯","৩:৫৮","৩:৫৭","৩:৫৫","৩:৫৪","৩:৫৩","৩:৫২","৩:৫১","৩:৫০","৩:৫০","৩:৪৯","৩:৪৯","৩:৪৮","৩:৪৮","৩:৪৭","৩:৪৭","৩:৪৬","৩:৪৬","৩:৪৫","৩:৪৪","৩:৪৪","৩:৪৩","৩:৪৩","৩:৪২"};
    String fazar[] = {"৪:১১","৪:১০","৪:০৯","৪:০৮","৪:০৭","৪:০৬","৪:০৫","৪:০৪","৪:০৩","৪:০০","৪:০০","৩:৫৯","৩:৫৮","৩:৫৭","৩:৫৬","৩:৫৬","৩:৫৫","৩:৫৫","৩:৫৪","৩:৫৪","৩:৫৩","৩:৫৩","৩:৫২","৩:৫২","৩:৫১","৩:৫০","৩:৫০","৩:৪৯","৩:৪৯","৩:৪৮"};
    String iftar[] = {"৬:২৮","৬:২৯","৬:২৯","৬:২৯","৬:৩০","৬:৩০","৬:৩১","৬:৩১","৬:৩২","৬:৩২","৬:৩৩","৬:৩৩","৬:৩৪","৬:৩৪","৬:৩৫","৬:৩৫","৬:৩৬","৬:৩৬","৬:৩৬","৬:৩৭","৬:৩৭","৬:৩৮","৬:৩৮","৬:৩৯","৬:৩৯","৬:৪০","৬:৪০","৬:৪১","৬:৪২","৬:৪২"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonSetAlarm = (Button)findViewById(R.id.setAlarm);
        karon = (Button)findViewById(R.id.karonBtn);
        sorto = (Button)findViewById(R.id.sortoBtn);
        doa = (Button)findViewById(R.id.doaBtn);
        tasbih = (Button)findViewById(R.id.tasbihBtn);
        about = (Button)findViewById(R.id.About);
        onnoelaka = (Button)findViewById(R.id.onnoElaka);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
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


        onnoelaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),OtherAreaActivity.class);
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    //Toast.makeText (MainActivity.this,"Ad loaded",Toast.LENGTH_LONG).show();
                } else {
                   //Toast.makeText(MainActivity.this,"Ad not loaded",Toast.LENGTH_LONG).show();
                    startActivity(intent2);
                }

            }
        });
        doa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd1.isLoaded()){
                    mInterstitialAd1.show();
                }
                else {
                    Intent intent2 = new Intent(getApplicationContext(),DoaActivity.class);
                    startActivity(intent2);
                }
            }
        });
        karon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd2.isLoaded()){
                    mInterstitialAd2.show();
                }
                else {
                    Intent intent3 = new Intent(getApplicationContext(),KaronActivity.class);
                    startActivity(intent3);
                }
            }
        });
        tasbih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.alasoft.tk.counter"));
                startActivity(intent);
            }
        });
        sorto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInterstitialAd3.isLoaded()){
                    mInterstitialAd3.show();
                }
                else {
                    Intent intent4 = new Intent(getApplicationContext(),SortoActivity.class);
                    startActivity(intent4);
                }
            }
        });
        buttonSetAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(openClockIntent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("প্রস্তুতকারক");
                alertDialog.setIcon(R.drawable.dev);
                alertDialog.setMessage("আল-আমিন তুষার এবং জাহিদুল ইসলাম \n" +
                        "কম্পিউটার বিজ্ঞান ও প্রকৌশল বিভাগ \n" +
                        "জাহাঙ্গীরনগর বিশ্ববিদ্যালয় \n" +
                        "প্রতিষ্ঠানঃ 3LabGo");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ঠিক আছে",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });


        tv = new TextView[total_cell];
        for(int i=0; i<total_cell; i++) {
            {
                String tvID = "tv" + (i+1);
                int resID = getResources().getIdentifier(tvID, "id", getPackageName());
                tv[i] = ((TextView) findViewById(resID));
                if(i<6){
                    tv[i].setTypeface(null, Typeface.BOLD);
                    tv[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                }
                else if(i%6==2){
                    tv[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                }
                else{
                    tv[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                }

            }
        }
        title = (TextView) findViewById(R.id.title_here);
        title.setText("রমজান সময়সূচী ২০২০");
        tv[0].setText("১৪৪১ হিজরি রমজান");
        tv[1].setText("২০২০ ইংরেজি");
        tv[2].setText("বার");
        tv[3].setText("সাহরীর শেষ সময়");
        tv[4].setText("ফজরের ওয়াক্ত শুরু");
        tv[5].setText("ইফতারের সময়");
        int j=0,k=0,l=0,m=0,n=0,o=0;
        for(int i=6; i<total_cell ; i=i+6){
            tv[i].setText(roza_no[j]);
            j++;
        }
        for(int i=7; i<total_cell ; i=i+6){
            tv[i].setText(date[k]);
            k++;
        }
        for(int i=8; i<total_cell ; i=i+6){
            tv[i].setText(day[l]);
            l++;
        }
        for(int i=9; i<total_cell ; i=i+6){
            tv[i].setText(sahri[m]);
            m++;
        }
        for(int i=10; i<total_cell ; i=i+6){
            tv[i].setText(fazar[n]);
            n++;
        }
        for(int i=11; i<total_cell ; i=i+6){
            tv[i].setText(iftar[o]);
            o++;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GregorianCalendar today = new GregorianCalendar();
        int todayMonth = today.get(Calendar.MONTH)+1;
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        //System.out.println("Date is--------------->"+todayDayOfMonth+"/"+todayMonth);
        if(todayMonth==5) {
            switch (todayDayOfMonth) {
                case 18:
                    startLine = 6;
                    break;
                case 19:
                    startLine = 12;
                    break;
                case 20:
                    startLine = 18;
                    break;
                case 21:
                    startLine = 24;
                    break;
                case 22:
                    startLine = 30;
                    break;
                case 23:
                    startLine = 36;
                    break;
                case 24:
                    startLine = 42;
                    break;
                case 25:
                    startLine = 48;
                    break;
                case 26:
                    startLine = 54;
                    break;
                case 27:
                    startLine = 60;
                    break;
                case 28:
                    startLine = 66;
                    break;
                case 29:
                    startLine = 72;
                    break;
                case 30:
                    startLine = 78;
                    break;
                case 31:
                    startLine = 84;
                    break;
                default:
                    startLine = 500;
                    break;
            }
            endLine = startLine + 6;
            if (startLine < 500) {
                for (int z = startLine; z < endLine; z++) {
                    tv[z].setBackgroundResource(R.drawable.border2);
                    tv[z].setTextColor(Color.parseColor("#ff0000"));
                }
            }
        }
        if(todayMonth==6){
            switch (todayDayOfMonth){
                case 1:
                    startLine = 90;
                    break;
                case 2:
                    startLine=96;
                    break;
                case 3:
                    startLine=102;
                    break;
                case 4:
                    startLine=108;
                    break;
                case 5:
                    startLine=114;
                    break;
                case 6:
                    startLine=120;
                    break;
                case 7:
                    startLine=126;
                    break;
                case 8:
                    startLine=132;
                    break;
                case 9:
                    startLine=138;
                    break;
                case 10:
                    startLine=144;
                    break;
                case 11:
                    startLine=150;
                    break;
                case 12:
                    startLine=156;
                    break;
                case 13:
                    startLine=162;
                    break;
                case 14:
                    startLine=168;
                    break;
                case 15:
                    startLine=174;
                    break;
                case 16:
                    startLine=180;
                    break;
                default:
                    startLine=500;
                    break;
            }
            endLine = startLine +6;
            if(startLine<500){
                for(int z=startLine;z<endLine;z++){
                    tv[z].setBackgroundResource(R.drawable.border2);
                    tv[z].setTextColor(Color.parseColor("#ff0000"));
                }
            }
        }
    }
}