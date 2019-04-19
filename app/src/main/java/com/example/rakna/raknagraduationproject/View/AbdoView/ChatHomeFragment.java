package com.example.rakna.raknagraduationproject.View.AbdoView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakna.raknagraduationproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatHomeFragment extends Fragment {


    public ChatHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat_home, container, false);
        return rootView;
    }

}
