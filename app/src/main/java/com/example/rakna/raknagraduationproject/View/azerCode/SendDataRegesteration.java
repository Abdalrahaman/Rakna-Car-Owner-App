package com.example.rakna.raknagraduationproject.View.azerCode;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azer on 2/2/2019.
 */

public class SendDataRegesteration extends StringRequest {

    private static final String SENDDATAURL="https://rakna-app.000webhostapp.com/car_owner.php";
    private Map<String,String> MapData;


    public SendDataRegesteration(int r_driver_licence,String r_name,String r_phonenumber,int r_car_licence ,
                                 String r_email,String r_password,int r_car_type,Response.Listener<String> listener) {
        super(Method.POST,SENDDATAURL, listener, null);

        Log.d("tag", "SendDataLogin:ssssssssssss "+String.valueOf(r_driver_licence)+
        " "+r_name +" " + r_phonenumber+String.valueOf(r_car_licence) + " "
        +r_email+ " " +r_password+r_car_type);

        MapData =new HashMap<>();
        MapData.put("driver_licence",String.valueOf(r_driver_licence));
        MapData.put("name",r_name);
        MapData.put("phone",r_phonenumber);
        MapData.put("car_licence",String.valueOf(r_car_licence));
        MapData.put("email",r_email);
        MapData.put("password",r_password);
        MapData.put("car_type",String.valueOf(r_car_type));
    }

    @Override
    protected Map<String, String> getParams()  {


        return MapData;
    }




}
