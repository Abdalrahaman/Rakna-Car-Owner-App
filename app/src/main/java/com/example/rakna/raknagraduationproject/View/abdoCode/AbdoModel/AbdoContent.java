package com.example.rakna.raknagraduationproject.View.abdoCode.AbdoModel;

import android.text.SpannableString;

public class AbdoContent {

    public interface ProView{

        void getUpdateMessage(SpannableString newMsg);

    }

    public interface ProPresenter{

        void changeMessageStyle(int msgId);
    }

    public interface ProModel{

        SpannableString MessageStyleOperation(int msgId);
    }

}
