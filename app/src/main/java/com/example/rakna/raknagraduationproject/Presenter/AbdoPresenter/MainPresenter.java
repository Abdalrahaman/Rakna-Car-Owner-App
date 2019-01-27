package com.example.rakna.raknagraduationproject.Presenter.AbdoPresenter;

import android.text.SpannableString;

import com.example.rakna.raknagraduationproject.Model.AbdoModel.MainModel;
import com.example.rakna.raknagraduationproject.Model.AbdoModel.ModelContent;
import com.example.rakna.raknagraduationproject.View.AbdoView.ViewContent;

public class MainPresenter implements PresenterContent {

    ModelContent model ;
    ViewContent view ;


    public MainPresenter(ViewContent newView) {
        model = new MainModel();
        view = newView ;
    }

    @Override
    public void changeMessageStyle(int msgId) {
        SpannableString msgUpdate = model.MessageStyleOperation(msgId);
        view.getUpdateMessage(msgUpdate);
    }
}
