package com.example.walli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash_activity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sf = getSharedPreferences("mySharedDB",MODE_PRIVATE);
        int status = sf.getInt("status",0);


            handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if(status == 1){
                        Intent intent=new Intent(Splash_activity.this,mainscreen.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(Splash_activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 5500);

    }
}