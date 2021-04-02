package a3labgo.tusar.maheramadan20;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
public class LunchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        Thread mythread = new Thread(){
            public void run()
            {
                try{
                    sleep(500);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }
}
