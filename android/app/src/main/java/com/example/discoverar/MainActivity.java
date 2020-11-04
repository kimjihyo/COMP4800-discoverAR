package com.example.discoverar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String authToken = LocalStorageManager.load(this, LocalStorageManager.AUTH_TOKEN);

        if (!authToken.isEmpty()) {
            startActivity(new Intent(this, ScanActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}