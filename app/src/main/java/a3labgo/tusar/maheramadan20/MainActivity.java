package a3labgo.tusar.maheramadan20;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import a3labgo.tusar.maheramadan20.TimerFragments.TimePickerFragment;


public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd,mInterstitialAd1,mInterstitialAd2,mInterstitialAd3;
    TextView arabicYear, englishDate, dayOfWeek, sahriLastTime, fazrTime, iftarTime;
    Button buttonSetAlarm,tasbih,doa,karon,sorto,about,onnoelaka;
    AdView mAdView;
//    private TextView mOutputText;
    ArrayList<String> roza_no;
    ArrayList<String> day;
    ArrayList<String> date;
    ArrayList<String> sahri;
    ArrayList<String> fazar;
    ArrayList<String> iftar;
    char[] numberList= {'০','১','২','৩','৪','৫','৬','৭','৮','৯'};

    int[] systemDate = new int[2];
    RecyclerView recyclerView;
    AdapterMainTable adapter;
    TextView titleHere;

//    Alarm
    private Button mOpenAlarmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(() -> {
            if (checkVersion()) {
                new Thread(() -> {
                    saveDataInFileFromWeb("dates.txt");
                    saveDataInFileFromWeb("fazar.txt");
                    saveDataInFileFromWeb("iftar.txt");
                    saveDataInFileFromWeb("sehri.txt");
                }).start();
            }
        }).start();
        ////////////////////////////year////////////////////////////
        Calendar calendar = Calendar.getInstance();
        String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
        String CurrentYearHijri = String.valueOf(calendar.get(Calendar.YEAR)-579);
        String yearString,yearStringHijri;
        char[] yearChar = new char[4];
        char[] yearCharHijri = new char[4];
        int i=0;
        for (char ch: currentYear.toCharArray()) {
            yearChar[i]=numberList[Character.getNumericValue(ch)];
            i++;
        }
        i=0;
        for (char ch: CurrentYearHijri.toCharArray()) {
            yearCharHijri[i]=numberList[Character.getNumericValue(ch)];
            i++;
        }
        yearString = new String(yearChar);
        yearStringHijri = new String(yearCharHijri);

        setTitle(getText(R.string.app_name)+" "+yearString);
        setContentView(R.layout.activity_main);


        ///////////////////////////////////////Data List////////////////////////////////////
        date = new ArrayList<>();
        roza_no = new ArrayList<>();
        sahri = new ArrayList<>();
        fazar = new ArrayList<>();
        iftar = new ArrayList<>();
        day = new ArrayList<>();

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    return;
                }
                // Get new Instance ID token
                String token = task.getResult().getToken();
                // Log and toast
                String msg = getString(R.string.msg_token_fmt, token);
            });

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
        titleHere = findViewById(R.id.title_here);
        titleHere.setText(getText(R.string.main_title)+" "+yearString);

        arabicYear.setText(R.string.roza_no);
        englishDate.setText(R.string.english_date);
        dayOfWeek.setText(R.string.day_of_week);
        sahriLastTime.setText(R.string.last_time_of_suhoor);
        fazrTime.setText(R.string.starting_time_fazr);
        iftarTime.setText(R.string.Iftaar_time);

        if(!readFomSavedFiles()){
            addAllData();
        }


        MobileAds.initialize(this, initializationStatus -> {
        });
        mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ///////////////////////////////////////////ad initialize/////////////////////////////////

        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd1 = new InterstitialAd(this);
        mInterstitialAd2 = new InterstitialAd(this);
        mInterstitialAd3 = new InterstitialAd(this);
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


        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                Intent intent2 = new Intent(getApplicationContext(), OtherAreaActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        mInterstitialAd1.setAdListener(new AdListener() {
            public void onAdClosed() {
                Intent intent2 = new Intent(getApplicationContext(), DoaActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd1.loadAd(new AdRequest.Builder().build());
            }
        });
        mInterstitialAd2.setAdListener(new AdListener() {
            public void onAdClosed() {
                Intent intent2 = new Intent(getApplicationContext(), KaronActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd2.loadAd(new AdRequest.Builder().build());
            }
        });
        mInterstitialAd3.setAdListener(new AdListener() {
            public void onAdClosed() {
                Intent intent2 = new Intent(getApplicationContext(), SortoActivity.class);
                startActivity(intent2);
                //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
                mInterstitialAd3.loadAd(new AdRequest.Builder().build());
            }
        });

        onnoelaka.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), OtherAreaActivity.class);
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                startActivity(intent2);
            }

        });

        doa.setOnClickListener(view -> {
            if (mInterstitialAd1.isLoaded()) {
                mInterstitialAd1.show();
            } else {
                Intent intent2 = new Intent(getApplicationContext(), DoaActivity.class);
                startActivity(intent2);
            }
        });

        karon.setOnClickListener(view -> {
            if (mInterstitialAd2.isLoaded()) {
                mInterstitialAd2.show();
            } else {
                Intent intent3 = new Intent(getApplicationContext(), KaronActivity.class);
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
            if (mInterstitialAd3.isLoaded()) {
                mInterstitialAd3.show();
            } else {
                Intent intent4 = new Intent(getApplicationContext(), SortoActivity.class);
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

        // ATTENTION: This was auto-generated to handle app links.(App Indexing)
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    private boolean readFomSavedFiles() {
        // set up variables
        String dateFile = "dates.txt";
        String sahriFile = "sehri.txt";
        String fazarFile = "fazar.txt";
        String iftarFile = "iftar.txt";
        String line;
        try {
            FileInputStream file = openFileInput(dateFile);
            FileInputStream file1 = openFileInput(sahriFile);
            FileInputStream file2 = openFileInput(fazarFile);
            FileInputStream file3 = openFileInput(iftarFile);

            BufferedReader input = new BufferedReader(new InputStreamReader(file));
            BufferedReader input1 = new BufferedReader(new InputStreamReader(file1));
            BufferedReader input2 = new BufferedReader(new InputStreamReader(file2));
            BufferedReader input3 = new BufferedReader(new InputStreamReader(file3));
            if (!input.ready() || !input2.ready() || !input3.ready() || !input1.ready() ) {
                throw new IOException();
            }else {
                date.clear();
                sahri.clear();
                fazar.clear();
                iftar.clear();
                while ((line = input.readLine()) != null) {
                    date.add(line);
                }
                while ((line = input1.readLine()) != null) {
                    sahri.add(line);
                }
                while ((line = input2.readLine()) != null) {
                    fazar.add(line);
                }
                while ((line = input3.readLine()) != null) {
                    iftar.add(line);
                }
                addfixedData();
            }
            input.close();
            input1.close();
            input2.close();
            input3.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void addAllData() {
        date.clear();
        date.add("২৫ এপ্রিল");
        date.add("২৬ এপ্রিল");
        date.add("২৭ এপ্রিল");
        date.add("২৮ এপ্রিল");
        date.add("২৯ এপ্রিল");
        date.add("৩০ এপ্রিল");
        date.add("১ মে");
        date.add("২ মে");
        date.add("৩ মে");
        date.add("৪ মে");
        date.add("৫ মে");
        date.add("৬ মে");
        date.add("৭ মে");
        date.add("৮ মে");
        date.add("৯ মে");
        date.add("১০ মে");
        date.add("১১ মে");
        date.add("১২ মে");
        date.add("১৩ মে");
        date.add("১৪ মে");
        date.add("১৫ মে");
        date.add("১৬ মে");
        date.add("১৭ মে");
        date.add("১৮ মে");
        date.add("১৯ মে");
        date.add("২০ মে");
        date.add("২১ মে");
        date.add("২২ মে");
        date.add("২৩ মে");
        date.add("২৪ মে");

        sahri.clear();
        sahri.add("৪:০৫");
        sahri.add("৪:০৪");
        sahri.add("৪:০৩");
        sahri.add("৪:০২");
        sahri.add("৪:০১");
        sahri.add("৪:০০");
        sahri.add("৩:৫৯");
        sahri.add("৩:৫৮");
        sahri.add("৩:৫৭");
        sahri.add("৩:৫৫");
        sahri.add("৩:৫৪");
        sahri.add("৩:৫৩");
        sahri.add("৩:৫২");
        sahri.add("৩:৫১");
        sahri.add("৩:৫০");
        sahri.add("৩:৫০");
        sahri.add("৩:৪৯");
        sahri.add("৩:৪৯");
        sahri.add("৩:৪৮");
        sahri.add("৩:৪৮");
        sahri.add("৩:৪৭");
        sahri.add("৩:৪৭");
        sahri.add("৩:৪৬");
        sahri.add("৩:৪৬");
        sahri.add("৩:৪৫");
        sahri.add("৩:৪৪");
        sahri.add("৩:৪৪");
        sahri.add("৩:৪৩");
        sahri.add("৩:৪৩");
        sahri.add("৩:৪২");

        fazar.clear();
        fazar.add("৪:১১");
        fazar.add("৪:১০");
        fazar.add("৪:০৯");
        fazar.add("৪:০৮");
        fazar.add("৪:০৭");
        fazar.add("৪:০৬");
        fazar.add("৪:০৫");
        fazar.add("৪:০৪");
        fazar.add("৪:০৩");
        fazar.add("৪:০০");
        fazar.add("৪:০০");
        fazar.add("৩:৫৯");
        fazar.add("৩:৫৮");
        fazar.add("৩:৫৭");
        fazar.add("৩:৫৬");
        fazar.add("৩:৫৬");
        fazar.add("৩:৫৫");
        fazar.add("৩:৫৫");
        fazar.add("৩:৫৪");
        fazar.add("৩:৫৪");
        fazar.add("৩:৫৩");
        fazar.add("৩:৫৩");
        fazar.add("৩:৫২");
        fazar.add("৩:৫২");
        fazar.add("৩:৫১");
        fazar.add("৩:৫০");
        fazar.add("৩:৫০");
        fazar.add("৩:৪৯");
        fazar.add("৩:৪৯");
        fazar.add("৩:৪৮");

        iftar.clear();
        iftar.add("৬:২৮");
        iftar.add("৬:২৯");
        iftar.add("৬:২৯");
        iftar.add("৬:২৯");
        iftar.add("৬:৩০");
        iftar.add("৬:৩০");
        iftar.add("৬:৩১");
        iftar.add("৬:৩১");
        iftar.add("৬:৩২");
        iftar.add("৬:৩২");
        iftar.add("৬:৩৩");
        iftar.add("৬:৩৩");
        iftar.add("৬:৩৪");
        iftar.add("৬:৩৪");
        iftar.add("৬:৩৫");
        iftar.add("৬:৩৫");
        iftar.add("৬:৩৬");
        iftar.add("৬:৩৬");
        iftar.add("৬:৩৬");
        iftar.add("৬:৩৭");
        iftar.add("৬:৩৭");
        iftar.add("৬:৩৮");
        iftar.add("৬:৩৮");
        iftar.add("৬:৩৯");
        iftar.add("৬:৩৯");
        iftar.add("৬:৪০");
        iftar.add("৬:৪০");
        iftar.add("৬:৪১");
        iftar.add("৬:৪২");
        iftar.add("৬:৪২");

        addfixedData();
    }

    private void addfixedData() {
        roza_no.clear();
        roza_no.add("১");
        roza_no.add("২");
        roza_no.add("৩");
        roza_no.add("৪");
        roza_no.add("৫");
        roza_no.add("৬");
        roza_no.add("৭");
        roza_no.add("৮");
        roza_no.add("৯");
        roza_no.add("১০");
        roza_no.add("১১");
        roza_no.add("১২");
        roza_no.add("১৩");
        roza_no.add("১৪");
        roza_no.add("১৫");
        roza_no.add("১৬");
        roza_no.add("১৭");
        roza_no.add("১৮");
        roza_no.add("১৯");
        roza_no.add("২০");
        roza_no.add("২১");
        roza_no.add("২২");
        roza_no.add("২৩");
        roza_no.add("২৪");
        roza_no.add("২৫");
        roza_no.add("২৬");
        roza_no.add("২৭");
        roza_no.add("২৮");
        roza_no.add("২৯");
        roza_no.add("৩০");

        day.clear();
        day.add("শনি");
        day.add("রবি");
        day.add("সোম");
        day.add("মঙ্গল");
        day.add("বুধ");
        day.add("বৃহস্পতি");
        day.add("শুক্র");
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
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "না", (dialog, which) -> dialog.cancel());
        }
        alertDialog.show();
    }

    private void saveDataInFileFromWeb(String filename) {
        FileOutputStream outputStream;
        String line;
        URL url = null;
        try {
            url = new URL("https://tusar.000webhostapp.com/ramdan/"+filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in;
        try {
            outputStream = openFileOutput(filename,Context.MODE_PRIVATE);
            in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(url).openStream()));
            int index=0;
            while ((line = in.readLine()) != null) {
                // do something with line
                if (index==0) {
                    outputStream.write(line.getBytes());
                }else {
                    outputStream.write(("\n"+line).getBytes());
                }
                index++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkVersion() {
        FileOutputStream outputStream;
        String line;
        //Read from Devie
        BufferedReader in;
        int currentVersion=0;
        try {
            FileInputStream file4 = openFileInput("version.txt");
            BufferedReader input4 = new BufferedReader(new InputStreamReader(file4));
            if (!input4.ready()) {
                throw new IOException();
            }else {
                while ((line = input4.readLine()) != null) {
                    currentVersion = Integer.parseInt(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read from Online
        URL url;
        try {
            url = new URL("https://tusar.000webhostapp.com/ramdan/version.txt");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        try {
            in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(url).openStream()));
            while ((line = in.readLine()) != null) {
                // do something with line //read here
                int onlineVersion = Integer.parseInt(line);
                if(currentVersion<onlineVersion){
                    outputStream = openFileOutput("version.txt",Context.MODE_PRIVATE);
                    outputStream.write(line.getBytes());
                    return true;
                }else {
                    return false;
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerviewMainPage);
        adapter = new AdapterMainTable(roza_no, day, date, sahri, fazar, iftar, systemDate, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Calendar calendar = Calendar.getInstance();
        systemDate[0] = calendar.get(Calendar.DAY_OF_MONTH);
        systemDate[1] = calendar.get(Calendar.MONTH);
        initRecyclerView();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        if((!(date.size()==29 || date.size()==30) && (sahri.size()==29 || sahri.size()==30) && (fazar.size()==29 || fazar.size()==30) && (iftar.size()==29 || iftar.size()==30))){
            addAllData();
        }
    }
}