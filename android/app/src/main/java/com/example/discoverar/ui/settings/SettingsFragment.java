package com.example.discoverar.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.discoverar.LocalStorageManager;
import com.example.discoverar.LoginActivity;
import com.example.discoverar.R;


public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        Button signOutButton = root.findViewById(R.id.settings_logout_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: sign out button clicked");
                LocalStorageManager.clear(getContext(), LocalStorageManager.AUTH_TOKEN);
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        return root;
    }
}
