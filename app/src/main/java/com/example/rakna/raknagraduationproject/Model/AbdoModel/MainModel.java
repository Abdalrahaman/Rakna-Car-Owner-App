package com.example.rakna.raknagraduationproject.Model.AbdoModel;

import android.text.SpannableString;

public class MainModel implements ModelContent {

    NotiMessageStyles msgSty ;
    SpannableString msgUpdate;

    public MainModel() {

    }

    @Override
    public SpannableString MessageStyleOperation(int msgId) {

        msgSty = new NotiMessageStyles();

        if (msgId == 0){

           msgUpdate = msgSty.warningMessageStyle();

        }else {

            msgUpdate = msgSty.chattingMessageStyle();
        }

        return msgUpdate;
    }
}
