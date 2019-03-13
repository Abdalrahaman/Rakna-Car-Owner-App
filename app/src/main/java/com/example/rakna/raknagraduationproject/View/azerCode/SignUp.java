package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.rakna.raknagraduationproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;


public class SignUp extends AppCompatActivity {

    private  static int SPLASH_TIME=2000;

    EditText name;
    EditText phoneNum;
    EditText licenseNumber;
    EditText driverLicense;
    EditText carType;
    EditText email;
    EditText pass1;
    EditText pass2;
    private Button loding;

    Button signup_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_butt=findViewById(R.id.SignUp);

        name = findViewById(R.id.Name);
        phoneNum = findViewById(R.id.PhoneNumber);
        licenseNumber = findViewById(R.id.LicenseNumber);
        driverLicense = findViewById(R.id.DriverLicense);
        carType = findViewById(R.id.CareType);
        email = findViewById(R.id.E_mail);
        pass1 = findViewById(R.id.Password);
        pass2 = findViewById(R.id.ConfirmPassword);
        loding=findViewById(R.id.progress1);

        signup_butt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
//                if (name.getText().toString().isEmpty()) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please enter the name",
//                            Toast.LENGTH_SHORT).show();
//                    return ;
//                }
//                if (phoneNum.getText().toString().isEmpty()) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please enter the phone Number",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (licenseNumber.getText().toString().isEmpty()) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please enter the license Number",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (driverLicense.getText().toString().isEmpty()) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please enter the driver License Number",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (carType.getText().toString().isEmpty()) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please enter the car Type",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (email.getText().toString().isEmpty()) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please enter the E_mail",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (pass1.getText().toString().isEmpty()) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please enter the password",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (pass1.getText().length() < 4) {
//
//                    Toast.makeText(SignUp.this,
//                            "Please password Must be more than 6 character",
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//        if (!Objects.equals(pass1.getText().toString(), pass2.getText().toString())) {
//            Toast.makeText(SignUp.this,
//                    "Please enter the correct password",
//                    Toast.LENGTH_SHORT).show();
//            return;
//
//        }

                loding.setVisibility(View.VISIBLE);
                signup_butt.setVisibility(View.GONE);

                final Animation animation= AnimationUtils.loadAnimation(SignUp.this,R.anim.rotate);
                loding.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Regester();

                        finish();
                    }
                },SPLASH_TIME);

            }
        });


    }

    public void Regester() {
//        loding.setVisibility(View.VISIBLE);
//        signup_butt.setVisibility(View.GONE);

        final String r_name = name.getText().toString().trim();
        final String r_phonenumber = phoneNum.getText().toString().trim();
        final int r_licenseNumber = Integer.parseInt(licenseNumber.getText().toString().trim());
        final int r_driverLicense = Integer.parseInt(driverLicense.getText().toString().trim());
        final int r_carType = Integer.parseInt(carType.getText().toString().trim());
        final String r_email = email.getText().toString().trim();
        final String r_pass1 = pass1.getText().toString().trim();


        Response.Listener<String> responselistner = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        Toast.makeText(SignUp.this,
                                "Regestration successful",
                                Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignUp.this, WelcomeActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(SignUp.this,
                                "Regestration failed",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUp.this,
                            "Regestration ERROR" + e.toString(),
                            Toast.LENGTH_LONG).show();

                    loding.setVisibility(View.GONE);
                    signup_butt.setVisibility(View.VISIBLE);
                }
            }
        };

        SendDataRegesteration sendDataRegesteration=new SendDataRegesteration(r_driverLicense,r_name,
                r_phonenumber,r_licenseNumber,r_email,r_pass1,r_carType,responselistner);

        RequestQueue requestQueue= Volley.newRequestQueue(SignUp.this);
        requestQueue.add(sendDataRegesteration);

    }
}
