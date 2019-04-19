package com.example.rakna.raknagraduationproject.View.AbdoView;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rakna.raknagraduationproject.R;

public class LockConnectionActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    private Button btnQRcode;
    private Button btnNfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_connection);

        initWidgets();
        actionWidgets();


        showAnimateConnection();

    }

    public void initWidgets(){

        lottieAnimationView = findViewById(R.id.animation_view);
        btnQRcode = findViewById(R.id.btn_qrCode);
        btnNfc = findViewById(R.id.btn_nfc);
    }

    public void actionWidgets(){

        btnQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LockConnectionActivity.this, QRcodeActivity.class);
                startActivity(intent);
            }
        });


        btnNfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LockConnectionActivity.this, NfcActivity.class);
                startActivity(intent);
            }
        });

    }


    public void showAnimateConnection(){
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                lottieAnimationView.cancelAnimation();

                lottieAnimationView.setAnimation("scan_nfc.json");
                lottieAnimationView.loop(false);
                lottieAnimationView.setProgress(0);
                lottieAnimationView.playAnimation();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @Override
    protected void onStop() {
        lottieAnimationView.cancelAnimation();
        super.onStop();
    }


    //    public void animate(View v) {
//        if (lottieAnimationView.isAnimating()) {
//            lottieAnimationView.cancelAnimation();
//            button.setText("play");
//        } else {
//            lottieAnimationView.playAnimation();
//            button.setText("pause");
//        }
//    }
}
