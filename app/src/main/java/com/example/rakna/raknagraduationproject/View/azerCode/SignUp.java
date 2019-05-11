package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static int SPLASH_TIME = 2000;

    HashMap<String, Integer> hashMap = new HashMap<>();
    int new_car_id;

    EditText name;
    EditText phoneNum;
    EditText licenseNumber;
    EditText driverLicense;
    EditText email;
    EditText pass1;
    EditText pass2;
    private Button loding;

    Button signup_butt;

    Spinner Spinner_car_model;
    Spinner Spinner_car_type;
    ArrayList<String> car_models_ = new ArrayList<String>();

    private final String URL_READ = "https://rakna-app.000webhostapp.com/car_mark.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_butt = findViewById(R.id.SignUp);

        name = findViewById(R.id.Name);
        phoneNum = findViewById(R.id.PhoneNumber);
        licenseNumber = findViewById(R.id.LicenseNumber);
        driverLicense = findViewById(R.id.DriverLicense);
        email = findViewById(R.id.E_mail);
        pass1 = findViewById(R.id.Password);
        pass2 = findViewById(R.id.ConfirmPassword);
        loding = findViewById(R.id.progress1);

        Spinner_car_type = findViewById(R.id.spinner_caretype);
        Spinner_car_model = findViewById(R.id.spinner_carmodel);

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

                final Animation animation = AnimationUtils.loadAnimation(SignUp.this, R.anim.rotate);
                loding.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Regester();

                        finish();
                    }
                }, SPLASH_TIME);

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.car_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_car_type.setAdapter(adapter);
        Spinner_car_type.setOnItemSelectedListener(SignUp.this);


    }

    public void Regester() {

        final String r_name = name.getText().toString().trim();
        final String r_phonenumber = phoneNum.getText().toString().trim();
        final int r_licenseNumber = Integer.parseInt(licenseNumber.getText().toString().trim());
        final int r_driverLicense = Integer.parseInt(driverLicense.getText().toString().trim());
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
                        Intent intent = new Intent(SignUp.this, WelcomeActivity.class);
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

        SendDataRegesteration sendDataRegesteration = new SendDataRegesteration(r_driverLicense, r_name,
                r_phonenumber, r_licenseNumber, r_email, r_pass1, new_car_id, responselistner);

        RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
        requestQueue.add(sendDataRegesteration);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String companyname = parent.getItemAtPosition(position).toString();
        getCarData(companyname);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    ////////////////////////////////////////////////////////////////

    private void getCarData(String companyname_) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i("hh", response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("car_info");
                    car_models_.clear();
                    hashMap.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                         int car_id = object.getInt("car_id");
                        String car_model = object.getString("car_model");
                        String car_width = object.getString("car_width");
                        String car_length = object.getString("car_length");
                        String car_height = object.getString("car_height");

                        car_models_.add(car_model);
                        hashMap.put(car_model, car_id);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUp.this, "Error read detail " + e.toString(), Toast.LENGTH_LONG).show();
                }


                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SignUp.this
                        ,android.R.layout.simple_spinner_item ,car_models_);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner_car_model.setAdapter(adapter1);


                Spinner_car_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String car_model=parent.getItemAtPosition(position).toString();

                        new_car_id = hashMap.get(car_model);

//                        Toast.makeText(SignUp.this, car_model + " " + new_car_id, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }

                });

            }

        },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SignUp.this, "Error read detail " + error.toString(), Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("company_name", companyname_);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
