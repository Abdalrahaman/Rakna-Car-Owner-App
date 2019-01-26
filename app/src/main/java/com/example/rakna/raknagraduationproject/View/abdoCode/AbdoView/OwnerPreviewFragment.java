package com.example.rakna.raknagraduationproject.View.abdoCode.AbdoView;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakna.raknagraduationproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerPreviewFragment extends Fragment {


    public OwnerPreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_owner_preview, container, false);

        FloatingActionButton parkingFinishedButton = (FloatingActionButton)rootView.findViewById(R.id.fab);

        parkingFinishedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPay = new Intent(getActivity() , PayActivity.class);
                startActivity(intentPay);
            }
        });

        return rootView ;
    }

}
