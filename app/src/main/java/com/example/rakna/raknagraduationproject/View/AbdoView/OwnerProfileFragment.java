package com.example.rakna.raknagraduationproject.View.AbdoView;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
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
import com.example.rakna.raknagraduationproject.View.azerCode.Login;
import com.example.rakna.raknagraduationproject.View.islamChat.MainActivity;
import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerProfileFragment extends Fragment {

    TextView user_name, phone, email, old, new_, confirm, lisence_num, driver_lisence;
    AlertDialog dialog, d1, d2, d3, d4, d5, d6;
    EditText editText, et1, et2, et3, et4, et5, et6;
    Button save;
    Spinner Spinner_car_model;
    Spinner Spinner_car_type;
    ArrayList<String> car_models_ = new ArrayList<String>();
    HashMap<String, Integer> hashMap = new HashMap<>();
    int new_car_id;
    // should be in bundle from login
    String email_ = "momo@gmail.com";


    private final String URL_READ = "https://rakna-app.000webhostapp.com/car_mark.php";
    private final String URL_READ_NAME_PHONE = "https://rakna-app.000webhostapp.com/select_car_owner_profile_new.php";
    private final String URL_UPDATE = "https://rakna-app.000webhostapp.com/update_profile.php";
    private final String URL_SELECT_CAR_MARK = "https://rakna-app.000webhostapp.com/select_car_mark_by_id.php";


    public OwnerProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_owner_profile, container, false);

        email = v.findViewById(R.id.email);
        save = v.findViewById(R.id.save);
        Spinner_car_type = v.findViewById(R.id.car_model);
        Spinner_car_model = v.findViewById(R.id.car_type);
        // email
        email.setText(email_);
        getNameAndPhone(email_);

        // user name dialog
        user_name = v.findViewById(R.id.user_name);
        dialog = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_BottomSheetDialog).create();
        editText = new EditText(getActivity());
        editText.setTextColor(Color.WHITE);
        dialog.setTitle(Html.fromHtml("<font color='#009688'>User name</font>"));
        dialog.setView(editText);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, " SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText().toString().trim().length() != 0)
                    user_name.setText(editText.getText());
            }
        });
        user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(user_name.getText().toString().trim());
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alert));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alert));
            }
        });


        // phone dialog
        phone = v.findViewById(R.id.phone);
        d1 = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_BottomSheetDialog).create();
        et1 = new EditText(getActivity());
        et1.setTextColor(Color.WHITE);
        d1.setTitle(Html.fromHtml("<font color='#009688'>Phone</font>"));
        d1.setView(et1);

        d1.setButton(DialogInterface.BUTTON_POSITIVE, " SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (et1.getText().toString().trim().length() != 0)
                    phone.setText(et1.getText());
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(phone.getText().toString().trim());
                d1.show();
                d1.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alert));
                d1.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alert));
            }
        });


        // old password dialog
        old = v.findViewById(R.id.old_);
        d2 = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_BottomSheetDialog).create();
        et2 = new EditText(getActivity());
        et2.setTextColor(Color.WHITE);
        et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        d2.setTitle(Html.fromHtml("<font color='#009688'>Old password</font>"));
        d2.setView(et2);

        d2.setButton(DialogInterface.BUTTON_POSITIVE, " SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (et2.getText().toString().trim().length() != 0)
                    old.setText(et2.getText());
                old.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d2.show();
                d2.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alert));
                d2.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alert));
            }
        });


        // new password dialog
        new_ = v.findViewById(R.id.new_);
        d3 = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_BottomSheetDialog).create();
        et3 = new EditText(getActivity());
        et3.setTextColor(Color.WHITE);
        et3.setTransformationMethod(PasswordTransformationMethod.getInstance());
        d3.setTitle(Html.fromHtml("<font color='#009688'>New password</font>"));
        d3.setView(et3);

        d3.setButton(DialogInterface.BUTTON_POSITIVE, " SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (et2.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "Please enter old password", Toast.LENGTH_SHORT).show();
                }
                else if (et3.getText().toString().trim().length() != 0) {
                    new_.setText(et3.getText());
                    new_.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        new_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d3.show();
                d3.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alert));
                d3.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alert));
            }
        });



        // confirm password dialog
        confirm = v.findViewById(R.id.confirm_);
        d4 = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_BottomSheetDialog).create();
        et4 = new EditText(getActivity());
        et4.setTextColor(Color.WHITE);
        et4.setTransformationMethod(PasswordTransformationMethod.getInstance());
        d4.setTitle(Html.fromHtml("<font color='#009688'>Confirm password</font>"));
        d4.setView(et4);

        d4.setButton(DialogInterface.BUTTON_POSITIVE, " SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (et3.getText().toString().trim().equals(et4.getText().toString().trim())){
                    if (et4.getText().toString().trim().length() != 0)
                        confirm.setText(et4.getText());
                    confirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else {
                    Toast.makeText(getActivity(), "Error confirm password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d4.show();
                d4.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alert));
                d4.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alert));
            }
        });



        // License num dialog
        lisence_num = v.findViewById(R.id.license_num);
        d5 = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_BottomSheetDialog).create();
        et5 = new EditText(getActivity());
        et5.setTextColor(Color.WHITE);
        d5.setTitle(Html.fromHtml("<font color='#009688'>License number</font>"));
        d5.setView(et5);

        d5.setButton(DialogInterface.BUTTON_POSITIVE, " SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (et5.getText().toString().trim().length() != 0)
                    lisence_num.setText(et5.getText());
            }
        });
        lisence_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d5.show();
                d5.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alert));
                d5.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alert));
            }
        });



        // Driver's license dialog
        driver_lisence = v.findViewById(R.id.driver_license);
        d6 = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_BottomSheetDialog).create();
        et6 = new EditText(getActivity());
        et6.setTextColor(Color.WHITE);
        d6.setTitle(Html.fromHtml("<font color='#009688'>Driver's license</font>"));
        d6.setView(et6);

        d6.setButton(DialogInterface.BUTTON_POSITIVE, " SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (et6.getText().toString().trim().length() != 0)
                    driver_lisence.setText(et6.getText());
            }
        });
        driver_lisence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d6.show();
                d6.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.alert));
                d6.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.alert));
            }
        });


        // Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.car_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_car_type.setAdapter(adapter);
        Spinner_car_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String companyname = parent.getItemAtPosition(position).toString();
                getCarData(companyname);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        // save
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String _driver_licence = driver_lisence.getText().toString().trim();
                final String _name = user_name.getText().toString().trim();
                final String _phone = phone.getText().toString().trim();
                final String _car_licence = lisence_num.getText().toString().trim();
                final String _password = new_.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email_);
                        params.put("driver_licence", _driver_licence);
                        params.put("name", _name);
                        params.put("phone", _phone);
                        params.put("car_licence", _car_licence);
                        params.put("password", _password);
                        params.put("car_id", String.valueOf(new_car_id));
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);

            }
        });


        return v;
    }


    private void getNameAndPhone(final String email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ_NAME_PHONE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("user_info");
                    JSONObject object = jsonArray.getJSONObject(0);
                    String name = object.getString("name");
                    String phone_ = object.getString("phone");
                    String driver_licence = object.getString("driver_licence");
                    String car_licence = object.getString("car_licence");
                    String car_id = object.getString("car_id");
                    user_name.setText(name);
                    phone.setText(phone_);
                    if (driver_licence.equals("null")){
                        driver_lisence.setText("Driver's license");
                    }else {
                        driver_lisence.setText(driver_licence);
                    }
                    if (car_licence.equals("null")){
                        lisence_num.setText("License number");
                    }else {
                        lisence_num.setText(car_licence);
                    }
                    if (!car_id.equals("null")){
                        getCarMark(car_id);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void getCarData(final String companyname_) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
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
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }


                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity()
                        , android.R.layout.simple_spinner_item, car_models_);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Spinner_car_model.setAdapter(adapter1);


                Spinner_car_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String car_model = parent.getItemAtPosition(position).toString();

                        new_car_id = hashMap.get(car_model);

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

                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("company_name", companyname_);

                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void getCarMark(final String car_id_) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SELECT_CAR_MARK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("car_info");
                    JSONObject object = jsonArray.getJSONObject(0);
                    String company_name = object.getString("company_name");
                    String car_model = object.getString("car_model");

                    Spinner_car_type.setSelection(((ArrayAdapter<String>)Spinner_car_type.getAdapter())
                            .getPosition(company_name));

                    getCarData(company_name);
                    Spinner_car_model.setSelection(car_models_.indexOf(car_model));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("car_id", car_id_);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
