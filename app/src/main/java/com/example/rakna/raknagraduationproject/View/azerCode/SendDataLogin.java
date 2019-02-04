package com.example.rakna.raknagraduationproject.View.azerCode;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Azer on 2/2/2019.
 */

public class SendDataLogin extends StringRequest {
    private static final String SENDDATAURL="https://rakna-app.000webhostapp.com/car_owner_login.php";
    private Map<String,String> MapData;

    public SendDataLogin(String email, String password, Response.Listener<String> listener) {
        super(Method.POST,SENDDATAURL , listener, null);
        Log.d("login", "SendDataLogin: email : " + email
            +" ,password : " + password);

        MapData =new HashMap<>();
        MapData.put("email",email);
        MapData.put("password",password);
    }
    @Override
    protected Map<String, String> getParams()  {
        return MapData;
    }
}
