package com.example.rakna.raknagraduationproject.View.azerCode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.hassan.MapsActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.collection.LLRBNode;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.facebook.CallbackManager;



public class Login extends AppCompatActivity {

    private static String URL_REGIST ="https://rakna-app.000webhostapp.com/reset_password_carowner.php";
    private static int SPLASH_TIME = 2000;

    Button Signin;
    EditText emailsign;
    EditText passwordsign;
    Button lodin;

    TextView signup;
    TextView forgot;

    //ImageView login_facebock;
    LoginButton login_facebock;
    ImageView login_google;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        lodin = findViewById(R.id.progresslogin);
        Signin = (Button) findViewById(R.id.SignInn);
        emailsign = (EditText) findViewById(R.id.E_mail_signinn);
        passwordsign = (EditText) findViewById(R.id.Password_signinn);

        signup = findViewById(R.id.createaccount);

        login_facebock = findViewById(R.id.facebock_login);
        login_google = findViewById(R.id.google_login);

        forgot = findViewById(R.id.forgot_password);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (emailsign.getText().toString().isEmpty()) {

                    Toast("Please enter the Email OR password");
                    return;
                }
                if (passwordsign.getText().toString().isEmpty()) {

                    Toast("Please enter the Email OR password");
                    return;
                }


                lodin.setVisibility(View.VISIBLE);
                Signin.setVisibility(View.GONE);

                final Animation animation = AnimationUtils.loadAnimation(Login.this, R.anim.rotate);
                lodin.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        login();

                    }

                }, SPLASH_TIME);


            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // startActivity(new Intent(Login.this, ForgotPasswordActivity.class));

                Intent i=new Intent(Login.this,ForgotPasswordcarowner.class);
                startActivity(i);
//                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this, SignUp.class));
//                finish();
            }
        });

        login_facebock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginFacebook();

            }
        });

        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void login() {

        String signEmail = emailsign.getText().toString().trim();
        String signPassword = passwordsign.getText().toString().trim();


        Response.Listener<String> responselistner = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    Log.d("success", "onResponse: success is " + success
                            + " ,JSON : " + jsonArray);

                    if (success) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("driver_licence").trim();
                            String name = object.getString("name").trim();
                            String email = object.getString("email").trim();
                            String rate = object.getString("overall_rate").trim();

                            sharedpref();

                            Intent intent = new Intent(Login.this, MapsActivity.class);
                            intent.putExtra("id",id);
                            intent.putExtra("name",name);
                            intent.putExtra("rate",rate);
                            startActivity(intent);

                            finish();
                            lodin.setVisibility(View.GONE);


                        }

                    }


                } catch (JSONException e) {

                    e.printStackTrace();

                    lodin.setVisibility(View.GONE);
                    Signin.setVisibility(View.VISIBLE);

                    Toast("LOGIN ERROR ");


                }
            }
        };
        SendDataLogin sendDataLogin = new SendDataLogin(signEmail, signPassword, responselistner);

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(sendDataLogin);

    }

    public void sharedpref() {
        getSharedPreferences("mydata", MODE_PRIVATE)
                .edit().
                putString("email", emailsign.getText().toString()).
                putString("pass", passwordsign.getText().toString()).
                commit();
    }

//    public void Clearsharedpref() {
//        getSharedPreferences("mydata", MODE_PRIVATE)
//                .edit().
//               clear().
//                commit();
//        Intent i = new Intent(getApplicationContext(), Login.class);
//        startActivity(i);
    //                    finish();
//    }



    private void registForFacebook(final String name, final String email, final String ID) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(Login.this, "Register success!", Toast.LENGTH_SHORT).show();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "Register error! 15 \n" + e.toString(), Toast.LENGTH_SHORT).show();

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Login.this, "Register error! 16 \n" + error.toString(), Toast.LENGTH_SHORT).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("name", name);
                params.put("email", email);
                params.put("id_number", ID);

                return params;
            }
        };
        // MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void getData(JSONObject object, String ID) {
        try {
            //URL profile_picture = new URL("https://graph.facebook.com/"+object.getString("id")+"picture?width=120&height=120");
            String photo = "https://graph.facebook.com/" + object.getString("id") + "/picture?type=normal";//"https://graph.facebook.com/"+object.getString("id")+"picture?width=120&height=120";
            String email = object.getString("email");
            String first_name = object.getString("first_name");
            String last_name = object.getString("last_name");
            String name = first_name + " " + last_name;

            Intent intent = new Intent(Login.this, MapsActivity.class);
            intent.putExtra("id","2");
            intent.putExtra("photo", photo);
            intent.putExtra("email", email);
            intent.putExtra("first_name", first_name);
            intent.putExtra("last_name", last_name);
            registForFacebook(name, email, ID);

            startActivity(intent);
            finish();

            Toast.makeText(this, "facebook : " + name + "\n" + email, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void loginFacebook() {

        callbackManager = CallbackManager.Factory.create();
        login_facebock.setReadPermissions(Arrays.asList("email", "public_profile"));
        login_facebock.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                lodin.setVisibility(View.GONE);

                final String ID = loginResult.getAccessToken().getUserId();

//                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        lodin.setVisibility(View.GONE);
                        Signin.setVisibility(View.VISIBLE);                        try {

                            Log.d("facebook", object.toString());


                            getData(object, ID);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, " error/n" + e, Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(Login.this, "here", Toast.LENGTH_LONG).show();


                    }
                });
                Bundle params = new Bundle();
                params.putString("fields", "first_name, last_name, email, id");
                graphRequest.setParameters(params);
                graphRequest.executeAsync();
            }


            @Override
            public void onCancel() {
                Toast.makeText(Login.this, "Canceled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }
    public void Toast(String text) {
        StyleableToast st = new StyleableToast(getApplicationContext(), text, Toast.LENGTH_LONG);
        st.setBackgroundColor(Color.parseColor("#e12a2a"));
        st.setTextColor(Color.WHITE);
        st.show();
    }

}
