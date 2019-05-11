package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rakna.raknagraduationproject.R;

public class FirstScreen extends AppCompatActivity {
    private  static int SPLASH_TIME=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        LottieAnimationView lottieAnimationView=findViewById(R.id.animation_view);
        ImageView imageView=findViewById(R.id.imagefirst);

        Animation animation= AnimationUtils.loadAnimation(this, R.anim.moveup);
        Animation animation1= AnimationUtils.loadAnimation(this, R.anim.fox);

        lottieAnimationView.setAnimation(animation);
        imageView.setAnimation(animation1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(FirstScreen.this, Login.class);
                startActivity(i);

                finish();
            }
        },SPLASH_TIME);
    }
}
