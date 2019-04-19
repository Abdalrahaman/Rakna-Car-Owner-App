package com.example.rakna.raknagraduationproject.View.AbdoView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.rakna.raknagraduationproject.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView ;
    private FloatingActionButton floatingActionButton;

    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        actionWidgets();
    }


    public void initWidgets(){

        navigationView = findViewById(R.id.bottom_navigation);
        floatingActionButton = findViewById(R.id.fab_exit_rakna);

        selectedFragment = new RaknaGarageFragment();
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
                            break;
                        case R.id.action_notification:
                            selectedFragment = new OwnerNotificationFragment();
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
//                Intent intent = new Intent(MainActivity.this, LockConnectionActivity.class);
//                startActivity(intent);
            }
        });

    }
}