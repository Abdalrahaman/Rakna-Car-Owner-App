package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.rakna.raknagraduationproject.R;

public class FirstScreen extends AppCompatActivity {
    private  static int SPLASH_TIME=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(FirstScreen.this,Login.class);
                startActivity(i);

                finish();
            }
        },SPLASH_TIME);
    }
}
