package com.example.discoverar.ui.journey;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.discoverar.LocalStorageManager;
import com.example.discoverar.R;
import com.example.discoverar.models.Journey;
import com.example.discoverar.ui.home.HomeFragment;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class JourneyFragment extends Fragment {
    private interface Callback {
        void onCompletion(String res);
    }

    private int journeyID = 169;
    private JourneyViewModel journeyViewModel;
    private String authToken = "";
    private static final String TAG = "JourneyFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        journeyViewModel =
                ViewModelProviders.of(this).get(JourneyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_journey, container, false);
        authToken = LocalStorageManager.load(getContext(), LocalStorageManager.AUTH_TOKEN);

        final TextView titleTextView = root.findViewById(R.id.journey_title);
        final TextView createdAtTextView = root.findViewById(R.id.journey_created_at);
        final TextView updatedAtTextView = root.findViewById(R.id.journey_updated_at);
        final Button deleteJourneyButton = root.findViewById(R.id.journey_delete_button);

        deleteJourneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Delete Journey pressed");
            }
        });

        Log.d(TAG, "onCreateView: hello world");
        try {
            getJourney(journeyID, new Callback() {
                @Override
                public void onCompletion(String res) {
                    Gson gson = new Gson();
                    Journey journey = gson.fromJson(res, Journey.class);
                    titleTextView.setText(journey.getTitle());
                    createdAtTextView.setText("Created at " + journey.getCreatedAt());
                    updatedAtTextView.setText("Updated at " + journey.getUpdatedAt());
                }
            });
        } catch (JSONException e) {
            Log.d(TAG, "onCreateView: " + e.getMessage());
        }

        return root;
    }

    private void getJourney(int id, final JourneyFragment.Callback callback) throws JSONException {
        if (authToken.isEmpty()) {
            return;
        }
        String url = "https://discover-ar-dev.herokuapp.com/api/journeys/" + id;

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