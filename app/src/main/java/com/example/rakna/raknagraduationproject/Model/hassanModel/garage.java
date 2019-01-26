package com.example.rakna.raknagraduationproject.Model.hassanModel;

import android.os.Handler;
import android.support.v4.view.ViewPager;

import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.hassan.Garag_Activity;
import com.example.rakna.raknagraduationproject.View.hassan.SlidingImage_Adapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class garage implements IGarage{

    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};


    @Override
    public Integer[] imageslist() {
        return IMAGES;
    }


}
