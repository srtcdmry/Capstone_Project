package com.dgpays.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.dgpays.myapplication.MainActivity;
import com.dgpays.myapplication.Utils.DatabaseInitializer;
import com.dgpays.myapplication.database.AppDatabase;
import com.dgpays.myapplication.databinding.ActivityLoginBinding;
import com.dgpays.myapplication.model.UserResource;
import com.dgpays.myapplication.repository.UserRepository;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    LoginViewModel viewModel;
    ProductResourcesInterface apiInterface;
    UserResource user = new UserResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
        binding.setListener(this);
        initClientRoom();
    }

    private void initClientRoom() {
        UserResource userResource = new UserResource();
        userResource = DatabaseInitializer.populateWithTestData2(AppDatabase.getAppDatabase(this));

        if (userResource != null) {
            binding.emailAdress.setText(userResource.getEmail());
            binding.password.setText(userResource.getPassword());
        }
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnLogin) {
            String eMail = binding.emailAdress.getText().toString();
            String password = binding.password.getText().toString();
            getUsers(eMail, password);
        } else if(view == binding.btnPassReset) {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        } else if (view == binding.btnSignUp) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }

    public void getUsers(String eMail, String password) {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.getUserResourceLiveData().observe(this, getAllUsers -> {
            if (getAllUsers != null) {
                for (int i = 0; i < getAllUsers.size(); i++) {
                    if (eMail.equals(getAllUsers.get(i).getEmail()) && password.equals(getAllUsers.get(i).getPassword())) {
                        user.setEmail(binding.emailAdress.getText().toString());
                        user.setPassword(binding.password.getText().toString());
                        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(getApplicationContext()), user);
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                        gotoMain();
                    }
                }
            }
        });
    }

    private void gotoMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}