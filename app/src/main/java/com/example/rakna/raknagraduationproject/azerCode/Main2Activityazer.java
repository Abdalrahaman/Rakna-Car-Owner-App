package com.example.rakna.raknagraduationproject.azerCode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.hassan.MapsActivity;

public class Main2Activityazer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2azer);
    }


    public void Gotomap(View view) {
        Intent i=new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(i);
    }
}
