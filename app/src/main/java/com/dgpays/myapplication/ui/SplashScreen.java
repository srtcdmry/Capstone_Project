package com.dgpays.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.dgpays.myapplication.Utils.DatabaseInitializer;
import com.dgpays.myapplication.database.AppDatabase;
import com.dgpays.myapplication.databinding.ActivitySplashScreenBinding;
import com.dgpays.myapplication.model.UserResource;
import com.dgpays.myapplication.ui.login.LoginActivity;
import com.dgpays.myapplication.ui.login.SignUpActivity;

public class SplashScreen extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkLoginOrSign();
    }

    public void checkLoginOrSign() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserResource userResource = new UserResource();
                userResource = DatabaseInitializer.populateWithTestData2(AppDatabase.getAppDatabase(getApplicationContext()));

                if (userResource != null) {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashScreen.this, SignUpActivity.class);
                    startActivity(intent);
                }
            }
        }, 2500);
    }
}