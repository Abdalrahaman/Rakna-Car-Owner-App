package com.example.rakna.raknagraduationproject.View.AbdoView;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rakna.raknagraduationproject.Model.AbdoModel.HistoryAdapter;
import com.example.rakna.raknagraduationproject.Model.AbdoModel.historyModel;
import com.example.rakna.raknagraduationproject.R;
import com.example.rakna.raknagraduationproject.View.islamChat.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerHistoryFragment extends Fragment {
//    RecyclerView recyclerView;
//
//    public ArrayList<historyModel> historyModelArrayList;
//    private String urlHistory = "https://rakna-app.000webhostapp.com/car_owner_history.php";
//
//    private String ownerId;

    public OwnerHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_owner_history, container, false);
//        recyclerView = view.findViewById(R.id.myhistoryrecycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        ownerId = getArguments().getString("driver_licence");
//
//        loadDataHistory();
//
//        HistoryAdapter historyAdapter = new HistoryAdapter(getActivity(), historyModelArrayList);
//        recyclerView.setAdapter(historyAdapter);
        return view;
    }

//    private void loadDataHistory() {
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlHistory,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            //catching array
//                            JSONArray array = new JSONArray(response);
//
//                            //loop through all objects
//                            for (int i = 0; i < array.length(); i++) {
//                                JSONObject history_items = array.getJSONObject(i);
//
//                                //adding items to list
//                                historyModelArrayList.add(new historyModel(
//                                        history_items.getString("name"),
//                                        history_items.getString("phone"),
//                                        history_items.getString("date"),
//                                        history_items.getString("from_time"),
//                                        history_items.getString("to_time"),
//                                        "Sea Street, Menofia Governorate",
//                                        history_items.getString("garage_image"),
//                                        history_items.getDouble("rate")
//                                ));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Errorredponse",error.getMessage());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("driver_licence", ownerId);
//                return params;
//            }
//        };
//
//        //adding string request to queue
//        queue.add(stringRequest);
//    }

}
