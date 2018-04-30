package com.example.nivitt.blinkingt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent i = new Intent(SplashScreen.this,DeviceList.class);
                    overridePendingTransition(R.anim.slide_right,R.anim.slide_out_right);
                    startActivity(i);

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
