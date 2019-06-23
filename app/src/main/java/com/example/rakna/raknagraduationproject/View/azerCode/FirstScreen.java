package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.hassan.MapsActivity;

public class FirstScreen extends AppCompatActivity {
    private static int SPLASH_TIME = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        SharedPreferences sharedPrefs = getSharedPreferences("mydata", MODE_PRIVATE);
        String PrefsEmail = sharedPrefs.getString("email", null);
        String PrefsPass = sharedPrefs.getString("pass", null);
        String ownerId = sharedPrefs.getString("ownerId", null);
        String ownerName = sharedPrefs.getString("ownerName", null);
        String ownerRate = sharedPrefs.getString("ownerRate", null);
        if (PrefsEmail != null && PrefsPass != null) {
            Intent intent = new Intent(FirstScreen.this, MapsActivity.class);
            intent.putExtra("ownerId",ownerId);
            intent.putExtra("ownerName",ownerName);
            intent.putExtra("ownerRate",ownerRate);
            startActivity(intent);

            finish();
        } else {

            LottieAnimationView lottieAnimationView = findViewById(R.id.animation_view);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.moveup);

            lottieAnimationView.setAnimation(animation);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(FirstScreen.this, Login.class);
                    startActivity(i);

                    finish();
                }
            }, SPLASH_TIME);
        }
    }
}
