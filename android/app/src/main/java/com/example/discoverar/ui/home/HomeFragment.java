package com.example.discoverar.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.discoverar.LocalStorageManager;
import com.example.discoverar.LoginActivity;
import com.example.discoverar.R;
import com.example.discoverar.models.Journey;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RecyclerView rv;
    private JourneyAdapter journeyAdapter;
    private HomeViewModel homeViewModel;
    private String authToken = "";
    private ArrayList<Journey> journeys;
    private interface Callback {
        void onCompletion(String res);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_library, container, false);

        rv = root.findViewById(R.id.journey_recycler_view);

        authToken = LocalStorageManager.load(getContext(), LocalStorageManager.AUTH_TOKEN);

        Log.d(TAG, "onCreateView: " + authToken);
        try {
            getJourneys(new Callback() {
                @Override
                public void onCompletion(String res) {
                   Gson gson = new Gson();
                   journeys = new ArrayList<>(Arrays.asList(gson.fromJson(res, Journey[].class)));

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    rv.setLayoutManager(linearLayoutManager);
                    journeyAdapter = new JourneyAdapter(journeys);
                    rv.setAdapter(journeyAdapter);


                    for (Journey journey : journeys) {
                        Log.d(TAG, "onCompletion: " + journey.getTitle());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return root;
    }

    private void getJourneys(final Callback callback) throws JSONException {
        if (authToken.isEmpty()) {
            return;
        }
        String url = "https://discover-ar-dev.herokuapp.com/api/journeys/viewAll";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                callback.onCompletion(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer "+ authToken);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}