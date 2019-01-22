package com.example.rakna.raknagraduationproject.abdoCode.AbdoModel;

import android.text.SpannableString;

public class MainModel implements AbdoContent.ProModel {

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
