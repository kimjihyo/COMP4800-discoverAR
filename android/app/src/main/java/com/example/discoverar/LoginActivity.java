package com.example.discoverar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText usernameOrEmailET;
    EditText passwordET;
    Button loginBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameOrEmailET = findViewById(R.id.usernameOrEmailID);
        passwordET = findViewById(R.id.passwordID);
        loginBTN = findViewById(R.id.login_screen_login_button);
    }

    public void loginClick(View view) throws JSONException {
        String usernameOrEmail = usernameOrEmailET.getText().toString();
        String password = passwordET.getText().toString();

        if (usernameOrEmail.matches("")) {
            Toast.makeText(this, "Please enter a username or email!", Toast.LENGTH_SHORT).show();
        } else if (password.matches("")) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else {
            System.out.println("------------------>>>>>> LOGGING IN!");
            login(usernameOrEmail, password);
        }
    }

    private void login(String usernameOrEmail, String password) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://discover-ar-dev.herokuapp.com/api/auth/signin";
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("usernameOrEmail", usernameOrEmail);
        jsonBody.put("password", password);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // HANDLE RESPONSE HERE!!!-------------------------------------------------
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // ERROR HANDLING HERE!!!----------------------------------------------------------
                System.out.println(R.string.invalid_auth);
            }
        });
        queue.add(stringRequest);
    }
}