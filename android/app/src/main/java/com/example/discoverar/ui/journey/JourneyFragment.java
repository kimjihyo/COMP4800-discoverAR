package com.example.discoverar.ui.journey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.example.discoverar.ScanActivity;
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

    private static JourneyFragment instance;

//    private int journeyID = 169;
    private Journey journey;
    private JourneyViewModel journeyViewModel;
    private String authToken = "";
    private static final String TAG = "JourneyFragment";


    private TextView titleTextView;
    private TextView descTextView;
    private ImageView triggerImageView;
    private TextView createdAtTextView;
    private TextView updatedAtTextView;
    private Button deleteJourneyButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        journeyViewModel =
                ViewModelProviders.of(this).get(JourneyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_journey, container, false);
        authToken = LocalStorageManager.load(getContext(), LocalStorageManager.AUTH_TOKEN);

        titleTextView = root.findViewById(R.id.journey_title);
        descTextView = root.findViewById(R.id.journey_description);
        triggerImageView = root.findViewById(R.id.journey_image);
        createdAtTextView = root.findViewById(R.id.journey_created_at);
        updatedAtTextView = root.findViewById(R.id.journey_updated_at);
        deleteJourneyButton = root.findViewById(R.id.journey_delete_button);

        instance = this;

        journey = ((ScanActivity)getContext()).getCurrentJourney();

        if (journey != null) {
            titleTextView.setText(journey.getTitle());
            descTextView.setText(journey.getDescription());
            createdAtTextView.setText("Created at " + journey.getCreatedAt());
            updatedAtTextView.setText("Updated at " + journey.getUpdatedAt());

            String s = journey.getImages()[0];
            String photoData = s.substring(s.indexOf(",") + 1);
            byte[] decodedString = Base64.decode(photoData.getBytes(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            triggerImageView.setImageBitmap(decodedByte);
        }

        deleteJourneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Delete Journey pressed");
            }
        });

        Log.d(TAG, "onCreateView: hello world");
//        try {
//            getJourney(journeyID, new Callback() {
//                @Override
//                public void onCompletion(String res) {
//                    Gson gson = new Gson();
//                    Journey journey = gson.fromJson(res, Journey.class);
//                    titleTextView.setText(journey.getTitle());
//                    descTextView.setText(journey.getDescription());
//                    createdAtTextView.setText("Created at " + journey.getCreatedAt());
//                    updatedAtTextView.setText("Updated at " + journey.getUpdatedAt());
//                }
//            });
//        } catch (JSONException e) {
//            Log.d(TAG, "onCreateView: " + e.getMessage());
//        }

        return root;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;

        titleTextView.setText(journey.getTitle());
        descTextView.setText(journey.getDescription());
        createdAtTextView.setText("Created at " + journey.getCreatedAt());
        updatedAtTextView.setText("Updated at " + journey.getUpdatedAt());
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

    public static JourneyFragment getInstance() {
        return instance;
    }
}