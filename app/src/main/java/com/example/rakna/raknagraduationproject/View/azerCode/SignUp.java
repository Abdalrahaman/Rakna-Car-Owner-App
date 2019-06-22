package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleableRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    ImageView backbutoon;
    CheckBox checkBox;
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
        backbutoon = findViewById(R.id.backbuton);
        checkBox = findViewById(R.id.checkBox);

        Spinner_car_type = findViewById(R.id.spinner_caretype);
        Spinner_car_model = findViewById(R.id.spinner_carmodel);

        backbutoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);

                finish();
            }
        });


        signup_butt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {


                loding.setVisibility(View.VISIBLE);
                signup_butt.setVisibility(View.GONE);

                final Animation animation = AnimationUtils.loadAnimation(SignUp.this, R.anim.rotate);
                loding.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!checkDataEntered())
                            Regester();

                        name.setText(name.getText().toString());
                        email.setText(email.getText().toString());
                        phoneNum.setText(phoneNum.getText().toString());
                        driverLicense.setText(driverLicense.getText().toString());
                        licenseNumber.setText(licenseNumber.getText().toString());
                        pass1.setText(pass1.getText().toString());
                        pass2.setText(pass2.getText().toString());


                        loding.setVisibility(View.GONE);
                        signup_butt.setVisibility(View.VISIBLE);

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
        final int r_licenseNumber;
        final int r_driverLicense;
        final String r_name = name.getText().toString().trim();
        final String r_phonenumber = phoneNum.getText().toString().trim();
        final String r_email = email.getText().toString().trim();
        final String r_pass1 = pass1.getText().toString().trim();

        if (licenseNumber.getText().toString().trim().equals("")) {
            r_licenseNumber=11111;
        }else {
            r_licenseNumber = Integer.parseInt(licenseNumber.getText().toString().trim());
        }
        if (driverLicense.getText().toString().trim().equals("")){
            r_driverLicense=11111;
        }else {
            r_driverLicense = Integer.parseInt(driverLicense.getText().toString().trim());

        }



        Response.Listener<String> responselistner = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {

                        Toast("Regestration successful");


                        Intent intent = new Intent(SignUp.this, Login.class);
                        startActivity(intent);

                        finish();
                        loding.setVisibility(View.GONE);
                        signup_butt.setVisibility(View.VISIBLE);


                    } else {

                        Toast("Regestration failed");

                        loding.setVisibility(View.GONE);
                        signup_butt.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    loding.setVisibility(View.GONE);
                    signup_butt.setVisibility(View.VISIBLE);


                    Toast("Regestration ERROR");


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
                    Toast("Check Internet Connection");
                }


                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(SignUp.this
                        , android.R.layout.simple_spinner_item, car_models_);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner_car_model.setAdapter(adapter1);


                Spinner_car_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String car_model = parent.getItemAtPosition(position).toString();

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

                        Toast("Error read detail ");

                    }
                }) {
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

    public boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString().trim();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString().trim();
        return TextUtils.isEmpty(str);
    }

    public static boolean isPasswordValidMethod(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static boolean isIdValidMethod(final String id) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^[0-9]{14}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(id);

        return matcher.matches();

    }

    public static boolean isPhoneNumberValidMethod(final String id) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^01[0-9]{9}(\\-)?[^0\\D]{1}\\d{6}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(id);

        return matcher.matches();

    }

    public boolean checkDataEntered() {
        boolean flag = false;
        if (isEmpty(name)) {
            name.setError("required!");
            flag = true;
        }
        if (isEmpty(name)) {
            name.setError("required!");
            flag = true;


        }
        if (isEmail(email) == false) {

            email.setError("Enter valid email!");
            flag = true;

        }
        if (pass1.getText().toString().trim().length() < 8 && !isPasswordValidMethod(pass1.getText().toString().trim())) {
            pass1.setError("Enter valid password");
            flag = true;

        }

        if (phoneNum.getText().toString().trim().length() < 11 && !isPhoneNumberValidMethod(phoneNum.getText().toString().trim())) {
            phoneNum.setError("Enter valid mobile number");
            flag = true;

        }
//        if (driverLicense.getText().toString().trim().length() < 4 && !isIdValidMethod(pass1.getText().toString().trim())) {
//            driverLicense.setError("Enter valid national id");
//            flag = true;
//
//        }
//        if (licenseNumber.getText().toString().trim().length() < 8 && !isIdValidMethod(pass1.getText().toString().trim())) {
//            licenseNumber.setError("Enter valid national id");
//            flag = true;
//
//
//        }
        if (!pass2.getText().toString().trim().equals(pass1.getText().toString().trim())) {
            pass2.setError("Enter the same passwor you entered");
            flag = true;

        }
        return flag;

    }

    public void checkaction(View view) {
        boolean checked = (checkBox).isChecked();
        if (checked) {
            signup_butt.setEnabled(true);

        } else {
            signup_butt.setEnabled(false);

        }

    }

    public void Toast(String text) {
        StyleableToast st = new StyleableToast(getApplicationContext(), text, Toast.LENGTH_LONG);
        st.setBackgroundColor(Color.parseColor("#e12a2a"));
        st.setTextColor(Color.WHITE);
        st.show();
    }
}
