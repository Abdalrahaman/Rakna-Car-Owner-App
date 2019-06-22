package com.example.rakna.raknagraduationproject.View.AbdoView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.islamChat.StartActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatHomeFragment extends Fragment {

    private Button loginChatByRakna;

    public ChatHomeFragment() {
        // Required empty public constructor
        //user : islam2ela4@gmail.com
        //pass : 123456789
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_chat_home, container, false);

        loginChatByRakna = rootView.findViewById(R.id.btn_loginChat_Rakna);

        loginChatByRakna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StartActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
