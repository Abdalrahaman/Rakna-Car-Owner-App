package com.example.rakna.raknagraduationproject.View.hassan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rakna.raknagraduationproject.Presenter.hassanPresenter.GaragePresenter;
import com.example.rakna.raknagraduationproject.Presenter.hassanPresenter.IGaragePresenter;
import com.example.rakna.raknagraduationproject.R;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rakna.raknagraduationproject.View.abdoCode.AbdoView.MainActivity;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Garag_Activity extends AppCompatActivity implements IGarageActivity{
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private Button reservation ;
    IGaragePresenter iGaragePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garag_);

        iGaragePresenter=new GaragePresenter(this);
        iGaragePresenter.Send_Images();

        reservation = (Button)findViewById(R.id.reserve);
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Garag_Activity.this, MainActivity.class));
            }
        });





    }




    public void Location(View view) {
        startActivity(new Intent(Garag_Activity.this,MapsActivity.class));
    }


    @Override
    public void init(Integer[] imgList) {


        for(int i=0;i<imgList.length;i++)
            ImagesArray.add(imgList[i]);

        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setAdapter(new SlidingImage_Adapter(Garag_Activity.this,ImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imgList.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }


}