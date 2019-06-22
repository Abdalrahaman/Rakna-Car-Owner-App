package com.example.rakna.raknagraduationproject.View.AbdoView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rakna.raknagraduationproject.R;

public class HomeServiceActivity extends AppCompatActivity {

    private BottomNavigationView navigationView ;
    private FloatingActionButton floatingActionButton;

    private String ownerId = "";
    private String garageId = "";
    private String garageName = "";
    private String garagePhone = "";
    private String garageRate = "";
    private String garageWidth = "";
    private String garageLength = "";
    private String serviceWash = "";
    private String serviceLubrication = "";
    private String serviceMaintenance = "";
    private String serviceOilChange = "";
    private boolean isReserved = false;

    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_service);

        savedInstanceState = getIntent().getExtras();
        ownerId = savedInstanceState.getString("ownerId");
        garageId = savedInstanceState.getString("garageId");
        garageName = savedInstanceState.getString("name");
        garagePhone = savedInstanceState.getString("phone");
        garageRate = savedInstanceState.getString("rate");
        garageWidth = savedInstanceState.getString("garage_width");
        garageLength = savedInstanceState.getString("garage_length");
        serviceWash = savedInstanceState.getString("wash");
        serviceLubrication = savedInstanceState.getString("lubrication");
        serviceMaintenance = savedInstanceState.getString("maintenance");
        serviceOilChange = savedInstanceState.getString("oil_change");
        isReserved = savedInstanceState.getBoolean("isReserved");

        Toast.makeText(this, ""+garageName, Toast.LENGTH_SHORT).show();

        initWidgets();
        actionWidgets();
    }


    public void initWidgets(){

        navigationView = findViewById(R.id.bottom_navigation);
        floatingActionButton = findViewById(R.id.fab_exit_rakna);

        selectedFragment = new RaknaGarageFragment();
        Bundle data = new Bundle();
        data.putString("garage_name", garageName);
        data.putString("garage_phone", garagePhone);
        data.putString("garage_rate", garageRate);
        data.putString("garage_width", garageWidth);
        data.putString("garage_length", garageLength);
        data.putString("wash", serviceWash);
        data.putString("lubrication", serviceLubrication);
        data.putString("maintenance", serviceMaintenance);
        data.putString("oil_change", serviceOilChange);
        selectedFragment.setArguments(data);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.action_rakna:
                            selectedFragment = new RaknaGarageFragment();
                            Bundle data = new Bundle();
                            data.putString("garage_name", garageName);
                            data.putString("garage_phone", garagePhone);
                            data.putString("garage_rate", garageRate);
                            data.putString("garage_width", garageWidth);
                            data.putString("garage_length", garageLength);
                            data.putString("wash", serviceWash);
                            data.putString("lubrication", serviceLubrication);
                            data.putString("maintenance", serviceMaintenance);
                            data.putString("oil_change", serviceOilChange);
                            selectedFragment.setArguments(data);
                            break;
                        case R.id.action_notification:
                            selectedFragment = new OwnerNotificationFragment();
                            break;
                        case R.id.action_profile:
                            selectedFragment = new OwnerProfileFragment();
                            break;
                        case R.id.action_camera:
                            selectedFragment = new OwnerPreviewFragment();
                            break;
                        case R.id.action_chat:
                            selectedFragment = new ChatHomeFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


    public void actionWidgets(){

        navigationView.setOnNavigationItemSelectedListener(navigationListener);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAttentionDialog();
            }
        });

    }

        //method to construct dialogue with scan results
    public void showAttentionDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Attention")
                .setMessage("Are you sure you need to get out of the garage")
                .setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        Intent intent = new Intent(HomeServiceActivity.this, LockConnectionActivity.class);
                        intent.putExtra("ownerId", ownerId);
                        intent.putExtra("garageId", garageId);
                        intent.putExtra("isReserved",false);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .show();
    }
}