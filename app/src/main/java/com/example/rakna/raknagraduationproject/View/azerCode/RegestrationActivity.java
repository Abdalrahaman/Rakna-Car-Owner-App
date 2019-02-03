package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rakna.raknagraduationproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegestrationActivity extends AppCompatActivity {

    Button Signin;
    Button Regester;

    EditText emailsign;
    EditText passwordsign;

    EditText name;
    EditText phoneNum;
    EditText licenseNumber;
    EditText driverLicense;
    EditText carType;
    EditText email;
    EditText pass1;
    EditText pass2;
    private ProgressBar loding;
    private static String URL_REGIST="https://rakna-app.000webhostapp.com/car_owner.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestration);

        Signin=(Button) findViewById(R.id.SignIn);
        Regester=(Button)findViewById(R.id.REgestre);

    Signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegestrationActivity.this);
            mBuilder.setTitle("Sign In");
            View mView = getLayoutInflater().inflate(R.layout.alertdialog_signin, null);
            emailsign = (EditText) mView.findViewById(R.id.E_mail_signin);
            passwordsign = (EditText) mView.findViewById(R.id.Password_signin);
            mBuilder.setView(mView);

            mBuilder.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                     String signEmail=emailsign.getText().toString().trim();
                     String signPassword=passwordsign.getText().toString().trim();

                    Response.Listener<String> responselistner = new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                boolean success = jsonObject.getBoolean("success");

                                if (success) {
                                    Toast.makeText(RegestrationActivity.this,
                                            "LOGIN successful",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(RegestrationActivity.this,
                                            "LOGIN failed",
                                            Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(RegestrationActivity.this,
                                        "LOGIN ERROR" + e.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    };

                  SendDataLogin sendDataLogin=new SendDataLogin(signEmail,signPassword,responselistner);

                    RequestQueue requestQueue= Volley.newRequestQueue(RegestrationActivity.this);
                    requestQueue.add(sendDataLogin);


                }
            });


            final AlertDialog dialog = mBuilder.create();
            dialog.show();
        }
    });

    //******************************************************************
    //******************************************************************

    Regester.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegestrationActivity.this);
            mBuilder.setTitle("Regestratoin");
            View mView = getLayoutInflater().inflate(R.layout.alertdialog_regester, null);
            name = (EditText)mView.findViewById(R.id.Name);
            phoneNum = (EditText) mView.findViewById(R.id.PhoneNumber);
            licenseNumber = (EditText) mView.findViewById(R.id.LicenseNumber);
            driverLicense = (EditText) mView.findViewById(R.id.DriverLicense);
            carType = (EditText) mView.findViewById(R.id.CareType);
            email = (EditText) mView.findViewById(R.id.E_mail);
            pass1 = (EditText) mView.findViewById(R.id.Password);
            pass2 = (EditText) mView.findViewById(R.id.ConfirmPassword);
            mBuilder.setView(mView);

            mBuilder.setPositiveButton("regester", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(DialogInterface dialog, int which) {



//                    if (name.getText().toString().isEmpty()) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the name",
//                                Toast.LENGTH_SHORT).show();
//                        return ;
//                    }
//                    if (phoneNum.getText().toString().isEmpty()) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the phone Number",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (licenseNumber.getText().toString().isEmpty()) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the license Number",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (driverLicense.getText().toString().isEmpty()) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the driver License Number",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (carType.getText().toString().isEmpty()) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the car Type",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (email.getText().toString().isEmpty()) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the E_mail",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (pass1.getText().toString().isEmpty()) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the password",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (pass1.getText().length() < 6) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please password Must be more than 6 character",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (!Objects.equals(pass1.getText().toString(), pass2.getText().toString())) {
//                        Toast.makeText(RegestrationActivity.this,
//                                "Please enter the correct password",
//                                Toast.LENGTH_SHORT).show();
//                        return;
//
//                }
                    Regester();
                }

            });

            mBuilder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            final AlertDialog dialog = mBuilder.create();
            dialog.show();

        }
    });

    }


    public void Regester() {
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
                        Toast.makeText(RegestrationActivity.this,
                                "Regestration successful",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegestrationActivity.this,
                                "Regestration failed",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegestrationActivity.this,
                            "Regestration ERROR" + e.toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        };

        SendDataRegesteration sendDataRegesteration=new SendDataRegesteration(r_driverLicense,r_name,
                r_phonenumber,r_licenseNumber,r_email,r_pass1,r_carType,responselistner);

        RequestQueue requestQueue= Volley.newRequestQueue(RegestrationActivity.this);
        requestQueue.add(sendDataRegesteration);

    }

//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject=new JSONObject(response);
//                            String success=jsonObject.getString("success");
//
//                           if(success.equals("1")){
//                                Toast.makeText(RegestrationActivity.this,
//                                        "Regestration successful",
//                                        Toast.LENGTH_LONG).show();
//                            }else {
//                               Toast.makeText(RegestrationActivity.this,
//                                       "Regestration failed",
//                                       Toast.LENGTH_LONG).show();
//                           }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(RegestrationActivity.this,
//                                    "Regestration ERROR"+e.toString(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Toast.makeText(RegestrationActivity.this,
//                                "Regestration ERROR"+error,
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//        RequestQueue requestQueue= Volley.newRequestQueue(RegestrationActivity.this);
//        requestQueue.add(stringRequest);
//
//    }




    public void SignIn(String Email, String Password){

        final String signEmail=emailsign.getText().toString();
        final String signPassword=passwordsign.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            JSONArray  jsonArray=jsonObject.getJSONArray("login");

                            if(success){
                                for (int i=0 ;i<jsonArray.length();i++){
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    String email=object.getString("email").trim();
                                    String password=object.getString("password").trim();
                                    Toast.makeText(RegestrationActivity.this,
                                            "login successful",
                                            Toast.LENGTH_LONG).show();
                                }

                            }else {
                                Toast.makeText(RegestrationActivity.this,
                                        "login failed",
                                        Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegestrationActivity.this,
                                    "Login ERROR"+e.toString(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(RegestrationActivity.this,
                                "Login ERROR"+error,
                                Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();




                params.put("email",signEmail);
                params.put("password",signPassword);


                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}
