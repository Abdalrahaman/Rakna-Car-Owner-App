package com.example.rakna.raknagraduationproject.View.AbdoView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rakna.raknagraduationproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RaknaGarageFragment extends Fragment {

    private TextView garageNameTextView, garageRateTextView, garageDetailsTextView, textViewCallingGarage;

    private String phone ;

    private String garageReservedService = "";

    public RaknaGarageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_rakna_garage, container, false);

        serviceGarageFormat();

        // initWidgets
        garageNameTextView = rootView.findViewById(R.id.garage_name);
        garageRateTextView = rootView.findViewById(R.id.like_rate);
        garageDetailsTextView = rootView.findViewById(R.id.details_info);

        // display garage info
        showGarageInfo();
        phone = getArguments().getString("garage_phone");

        textViewCallingGarage = rootView.findViewById(R.id.bt_call_garage);
        textViewCallingGarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });
        return rootView;
    }


    public void serviceGarageFormat(){

        if ("1".equals(getArguments().getString("wash")))
            garageReservedService += getString(R.string.wash) + " - ";
        if ("1".equals(getArguments().getString("lubrication")))
            garageReservedService += getString(R.string.lubrication) + " - ";
        if ("1".equals(getArguments().getString("maintenance")))
            garageReservedService += getString(R.string.maintenance) + " - ";
        if ("1".equals(getArguments().getString("oil_change")))
            garageReservedService += getString(R.string.oil_change) + " - ";

        // delete - from last string
        if (!"".equals(garageReservedService))
            garageReservedService = garageReservedService.substring(0,garageReservedService.length() - 2);

    }


    public void showGarageInfo(){
        // DELETE FROM `car_owner_history` WHERE driver_licence = 552
        garageNameTextView.setText(getArguments().getString("garage_name"));
        garageRateTextView.setText(getArguments().getString("garage_rate"));
        garageDetailsTextView.setText(getString(R.string.garage_measure)
                + getArguments().getString("garage_width") + " x " + getArguments().getString("garage_length")
                + getString(R.string.garage_service)
                + garageReservedService
                + getString(R.string.garage_location) + "Sea Street - melig - menofia");
    }
}
