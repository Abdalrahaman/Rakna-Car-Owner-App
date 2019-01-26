package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.hassan.MapsActivity;

public class Main2Activityazer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2azer);
    }

//kkk
    public void Gotomap(View view) {
        Intent i=new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(i);
    }
}
