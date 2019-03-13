package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.hassan.MapsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {

    private  static int SPLASH_TIME=2000;

    Button Signin;
    EditText emailsign;
    EditText passwordsign;
    Button lodin;

    TextView signup;
    TextView forgot;

    ImageView login_facebock;
    ImageView login_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lodin=findViewById(R.id.progresslogin);
        Signin=(Button) findViewById(R.id.SignInn);
        emailsign = (EditText) findViewById(R.id.E_mail_signinn);
        passwordsign = (EditText) findViewById(R.id.Password_signinn);

        signup =findViewById(R.id.createaccount);

        login_facebock=findViewById(R.id.facebock_login);
        login_google=findViewById(R.id.google_login);

        forgot=findViewById(R.id.forgot_password);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (emailsign.getText().toString().isEmpty()) {

                    Toast.makeText(Login.this,
                            "Please enter the Email OR password",
                            Toast.LENGTH_SHORT).show();
                    return ;
                }
                if (passwordsign.getText().toString().isEmpty()) {

                    Toast.makeText(Login.this,
                            "Please enter the Email OR password",
                            Toast.LENGTH_SHORT).show();
                    return ;
                }
                lodin.setVisibility(View.VISIBLE);
                Signin.setVisibility(view.GONE);

                final Animation animation= AnimationUtils.loadAnimation(Login.this,R.anim.rotate);
                lodin.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        login();

                        finish();
                    }

                },SPLASH_TIME);


            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this,SignUp.class));


            }
        });

        login_facebock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public  void login(){
//        lodin.setVisibility(View.VISIBLE);
//        Signin.setVisibility(view.GONE);

        String signEmail=emailsign.getText().toString().trim();
        String signPassword=passwordsign.getText().toString().trim();


        Response.Listener<String> responselistner = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    Log.d("success", "onResponse: success is " + success
                            +" ,JSON : " + jsonArray);

                    if (success) {

                        for (int i=0 ; i<jsonArray.length() ; i++){

                            JSONObject object=jsonArray.getJSONObject(i);
                            String name=object.getString("name").trim();
                            String email=object.getString("email").trim();

//                                    Toast.makeText(Login.this,
//                                            "login successful" +
//                                                    "YourName : " + name + "\n ,Email : " + email,
//                                            Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(Login.this, WelcomeActivity.class);
                            startActivity(intent);


                            lodin.setVisibility(View.GONE);

                        }

                    } else {
                        Toast.makeText(Login.this,
                                "LOGIN failed",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this,
                            "LOGIN ERROR " + e.toString(),
                            Toast.LENGTH_LONG).show();

                }
            }
        };
        SendDataLogin sendDataLogin=new SendDataLogin(signEmail,signPassword,responselistner);

        RequestQueue requestQueue= Volley.newRequestQueue(Login.this);
        requestQueue.add(sendDataLogin);

    }
}
