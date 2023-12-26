package com.example.vergo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vergo.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.homemenu) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.editmenu) {
                replaceFragment(new EditFragment());
            } else if (itemId == R.id.historymenu) {
                replaceFragment(new HistoryFragment());
            } else if (itemId == R.id.settingmenu) {
                replaceFragment(new SettingFragment());
            }
            return true;

        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragementTransaction =fragmentManager.beginTransaction();
        fragementTransaction.replace(R.id.frame_layout, fragment);
        fragementTransaction.commit();
    }
}