package com.example.rakna.raknagraduationproject.azerCode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rakna.raknagraduationproject.R;

public class Main2Activityazer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2azer);
    }


    public void Gotomap(View view) {
        Intent intent=null ,chooser=null;

        intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:19.076,72.8777"));
        chooser= Intent.createChooser(intent,"launch maps");
        startActivity(intent);
    }
}
