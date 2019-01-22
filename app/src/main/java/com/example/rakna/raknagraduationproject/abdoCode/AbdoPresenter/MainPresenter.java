package com.example.rakna.raknagraduationproject.abdoCode.AbdoPresenter;

import android.text.SpannableString;

import com.example.rakna.raknagraduationproject.abdoCode.AbdoModel.AbdoContent;
import com.example.rakna.raknagraduationproject.abdoCode.AbdoModel.MainModel;

public class MainPresenter implements AbdoContent.ProPresenter {

    AbdoContent.ProModel model ;
    AbdoContent.ProView view ;


    public MainPresenter(AbdoContent.ProView newView) {
        model = new MainModel();
        view = newView ;
    }

    @Override
    public void changeMessageStyle(int msgId) {
        SpannableString msgUpdate = model.MessageStyleOperation(msgId);
        view.getUpdateMessage(msgUpdate);
    }
}
