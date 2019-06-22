package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPasswordcarowner extends AppCompatActivity {
    EditText new_password, confirm_password;
    Button btn_reset;
    ProgressBar loading;
    private static String URL_REGIST = "https://rakna-app.000webhostapp.com/reset_password_carowner.php";//"https://rakna-app.000webhostapp.com/register.php";
    String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passwordcarowner);

        new_password = findViewById(R.id.new_pass);
        confirm_password = findViewById(R.id.confirm_password);
        btn_reset = findViewById(R.id.btn_reset);
        loading = findViewById(R.id.loading);

        Intent intent =getIntent();
        email = intent.getStringExtra("email").trim();
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkDataEntered()) {
                    resetPassword();
                }
            }
        });
    }

    private void resetPassword(){
        loading.setVisibility(View.VISIBLE);
        btn_reset.setVisibility(View.GONE);
//        Intent intent = getIntent();
        final String emailToSend = email;//"s.enghazemallm@gmail.com";//intent.getStringExtra("email");
        final String newPassword = this.new_password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
//                    String success = jsonObject.getString("success");
//                    if(success.equals("1")){
                    Toast("Register success!");
                    loading.setVisibility(View.GONE);
                    btn_reset.setVisibility(View.VISIBLE);



//                    }
                }catch (JSONException e){
                    e.printStackTrace();
//                    Toast.makeText(RegistrationActivity.this, "Register error! 15 \n"+ e.toString(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ResetPasswordcarowner.this,  Login.class));
                    finish();
                    Toast("success");
                    loading.setVisibility(View.GONE);
                    btn_reset.setVisibility(View.VISIBLE);
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ResetPasswordcarowner.this, "Register error! 16 \n"+ error.toString(),Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                btn_reset.setVisibility(View.VISIBLE);

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("email", email);
                params.put("password", newPassword);


                return params;
            }
        };
        // MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public static boolean isPasswordValidMethod(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public boolean checkDataEntered(){
        boolean flag = false;

        if (new_password.getText().toString().trim().length() < 8 && !isPasswordValidMethod(new_password.getText().toString().trim()) ){
            new_password.setError("Enter valid password");
            flag = true;
        }

        if(!confirm_password.getText().toString().trim().equals(new_password.getText().toString().trim())){
            confirm_password.setError("Enter the same passwor you entered");
            flag = true;
        }
        return flag;
    }
    public void Toast(String text) {
        StyleableToast st = new StyleableToast(getApplicationContext(), text, Toast.LENGTH_LONG);
        st.setBackgroundColor(Color.parseColor("#e12a2a"));
        st.setTextColor(Color.WHITE);
        st.show();
    }
}
