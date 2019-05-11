package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.AbdoView.HomeServiceActivity;


public class WelcomeActivity extends AppCompatActivity {
    private  static int SPLASH_TIME=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(WelcomeActivity.this, HomeServiceActivity.class);
                startActivity(i);

                finish();
            }
        },SPLASH_TIME);

    }
}