package com.dgpays.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dgpays.myapplication.Utils.DatabaseInitializer;
import com.dgpays.myapplication.database.AppDatabase;
import com.dgpays.myapplication.databinding.ActivitySignUpBinding;
import com.dgpays.myapplication.model.LoginResource;
import com.dgpays.myapplication.model.UserResource;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySignUpBinding binding;
    ProductResourcesInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
        binding.setListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnSignUp) {
            getNewUsers();
        } else if (view == binding.btnGoLogin) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void getNewUsers() {
        LoginResource loginResource = new LoginResource();
        apiInterface.getNewUsers(loginResource).enqueue(new Callback<List<LoginResource>>() {
            @Override
            public void onResponse(Call<List<LoginResource>> call, Response<List<LoginResource>> response) {
                Toast.makeText(SignUpActivity.this, "Sign up success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<LoginResource>> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}