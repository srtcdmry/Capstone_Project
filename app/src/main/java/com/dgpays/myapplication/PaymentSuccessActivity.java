package com.dgpays.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dgpays.myapplication.databinding.ActivityPaymentSuccessBinding;

public class PaymentSuccessActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityPaymentSuccessBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnShoppingGo) {
            Intent intent = new Intent(PaymentSuccessActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}