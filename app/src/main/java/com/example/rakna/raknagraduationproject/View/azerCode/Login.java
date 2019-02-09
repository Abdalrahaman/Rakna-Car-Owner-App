package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button Signin;
    EditText emailsign;
    EditText passwordsign;
    ProgressBar lodin;

    TextView signup;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lodin=findViewById(R.id.progresslogin);
        Signin=(Button) findViewById(R.id.SignInn);
        emailsign = (EditText) findViewById(R.id.E_mail_signinn);
        passwordsign = (EditText) findViewById(R.id.Password_signinn);

        signup =findViewById(R.id.createaccount);
        forgot=findViewById(R.id.forgot_password);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

               lodin.setVisibility(View.VISIBLE);
                Signin.setVisibility(view.GONE);

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


                                    lodin.setVisibility(view.GONE);

                                }

                            } else {
                                Toast.makeText(Login.this,
                                        "LOGIN failed",
                                        Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this,
                                    "LOGIN ERROR vvvvvvv" + e.toString(),
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                };
                SendDataLogin sendDataLogin=new SendDataLogin(signEmail,signPassword,responselistner);

                RequestQueue requestQueue= Volley.newRequestQueue(Login.this);
                requestQueue.add(sendDataLogin);


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
    }
}
