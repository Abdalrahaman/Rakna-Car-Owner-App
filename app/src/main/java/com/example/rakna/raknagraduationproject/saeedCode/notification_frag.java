package com.example.rakna.raknagraduationproject.saeedCode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class notification_frag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_frag_saeed);
        RecyclerView recyclerView = findViewById(R.id.myrecycler);
        itemData items[] = {new itemData("&lt;b>Hassan ElKhadrawy&lt;/b> recently made a rent request.",R.drawable.hassan),
                new itemData("&lt;b>Hassan ElKhadrawy&lt;/b> recently made a rent request.",R.drawable.hazem),
                new itemData("&lt;b>Hassan ElKhadrawy&lt;/b> recently made a rent request.",R.drawable.azer),
                new itemData("&lt;b>Hassan ElKhadrawy&lt;/b> recently made a rent request.",R.drawable.omran)};
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NotifyAdapter nadapter= new NotifyAdapter(items);
        recyclerView.setAdapter(nadapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
