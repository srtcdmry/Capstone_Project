package com.dgpays.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dgpays.myapplication.R;
import com.dgpays.myapplication.databinding.ActivityForgotPasswordBinding;
import com.dgpays.myapplication.model.LoginResource;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    ProductResourcesInterface apiInterface;
    ActivityForgotPasswordBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setListener(this);
        apiInterface = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);

    }

    public void getSendRequest() {
        LoginResource loginResource = new LoginResource();
        apiInterface.getForgotPassword(loginResource).enqueue(new Callback<List<LoginResource>>() {
            @Override
            public void onResponse(Call<List<LoginResource>> call, Response<List<LoginResource>> response) {
                Toast.makeText(ForgotPasswordActivity.this, "Password reset link has been sent to your e-mail address.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<LoginResource>> call, Throwable t) {
                Toast.makeText(ForgotPasswordActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnSend) {
            getSendRequest();
        }
    }
}