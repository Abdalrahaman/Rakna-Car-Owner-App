package com.example.rakna.raknagraduationproject.Model.AbdoModel;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;

public class NotiMessageStyles {

    String msgWarning ;
    String msgChatting ;


    StyleSpan boldSpan ;


    public NotiMessageStyles() {

        msgWarning = "There is one approaching your car, please see it in Garage Owner garage." ;
        msgChatting = "The Garage Owner want to call you !";

        boldSpan = new StyleSpan(Typeface.BOLD);
    }

    public SpannableString warningMessageStyle(){

        SpannableString ss = new SpannableString(msgWarning);

        ss.setSpan(boldSpan, 52, 64, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss ;

    }


    public SpannableString chattingMessageStyle(){

        SpannableString ss = new SpannableString(msgChatting);

        ss.setSpan(boldSpan, 4, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss ;

    }
}
