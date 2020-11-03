package com.example.discoverar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountCreationActivity extends AppCompatActivity {

    private Button backButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        firstNameEditText = findViewById(R.id.sign_up_screen_first_name);
        lastNameEditText = findViewById(R.id.sign_up_screen_last_name);
        emailEditText = findViewById(R.id.sign_up_screen_email_address);
        passwordEditText = findViewById(R.id.sign_up_screen_password);
        createAccountButton = findViewById(R.id.sign_up_screen_create_account_button);


        backButton = (Button) findViewById(R.id.sign_up_screen_go_bacl_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onCreateAccountClick();
            }
        });
    }

    private void onCreateAccountClick() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String username = firstName + " " + lastName;

        try {
            signUp(username, email, password);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    private void signUp(String username, String email, String password) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://discover-ar-dev.herokuapp.com/api/auth/signup";
        JSONObject jsonBody = new JSONObject();

        jsonBody.put("username", username);
        jsonBody.put("email", email);
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