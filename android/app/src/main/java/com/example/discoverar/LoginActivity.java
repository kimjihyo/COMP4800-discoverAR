package com.example.discoverar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.EditText;
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
    TextView signUpButton;
    Button loginBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signUpButton = (TextView) findViewById(R.id.login_screen_sign_up_button);

        signUpButton.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, AccountCreationActivity.class));
            }
        });
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
                System.out.println("Failed");
                System.out.println(R.string.invalid_auth);
                System.out.println(error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
}