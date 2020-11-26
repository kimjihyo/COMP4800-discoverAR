package com.example.discoverar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.discoverar.models.Discovery;
import com.example.discoverar.ui.home.HomeFragment;
import com.example.discoverar.ui.journey.JourneyFragment;
import com.example.discoverar.ui.scan.ScanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class ScanActivity extends AppCompatActivity {

    private String[] currentImageArr;
    private Discovery[] currentDiscoveries;

    private final String TAG = "ScanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        Log.d(TAG, "onCreate: " + navView.toString());

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_Library:
                        Log.d(TAG, "onNavigationItemSelected: navigation library");
                        selectedFragment = new HomeFragment();
                        // selectedFragment = new
                        break;
                    case R.id.navigation_Journey:
                        Log.d(TAG, "onNavigationItemSelected: navigation journey");
                        selectedFragment = new JourneyFragment();
                        break;
                    case R.id.navigation_scan:
                        Log.d(TAG, "onNavigationItemSelected: navigation scan");
                        selectedFragment = new ScanFragment();
                       break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_Library, R.id.navigation_Journey, R.id.navigation_scan)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
        currentImageArr = new String[0];
        currentDiscoveries = new Discovery[0];
    }
    public void setCurrentImageArr(String[] currentImageArr) { this.currentImageArr = currentImageArr; }
    public String[] getCurrentImageArr() { return this.currentImageArr; }

    public void setCurrentDiscoveries(Discovery[] currentDiscoveries) { this.currentDiscoveries = currentDiscoveries; }
    public Discovery[] getCurrentDiscoveries() { return this.currentDiscoveries; }

}