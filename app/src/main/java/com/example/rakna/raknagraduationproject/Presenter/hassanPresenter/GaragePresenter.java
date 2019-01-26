package com.example.rakna.raknagraduationproject.Presenter.hassanPresenter;

import com.example.rakna.raknagraduationproject.Model.hassanModel.garage;
import com.example.rakna.raknagraduationproject.View.hassan.IGarageActivity;

public class GaragePresenter implements IGaragePresenter {

  IGarageActivity iGarageActivity;

    public GaragePresenter(IGarageActivity iGarageActivity) {
        this.iGarageActivity = iGarageActivity;
    }

    @Override
    public void Send_Images() {


        garage garage=new garage();
        Integer[] list_images=garage.imageslist();

       iGarageActivity.init(list_images);


    }
}
