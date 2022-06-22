package com.dgpays.myapplication.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dgpays.myapplication.dao.UserDao;
import com.dgpays.myapplication.database.AppDatabase;
import com.dgpays.myapplication.model.UserResource;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;
import com.dgpays.myapplication.ui.login.LoginActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private final ProductResourcesInterface apiRequest;
    private LiveData<List<UserResource>> getAllUsers;

    public UserRepository(Application application) {
        apiRequest = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
    }

    public LiveData<List<UserResource>> userResponse() {
        final MutableLiveData<List<UserResource>> data = new MutableLiveData<>();
        apiRequest.getAllUsers()
                .enqueue(new Callback<List<UserResource>>() {
                    @Override
                    public void onResponse(Call<List<UserResource>> call, Response<List<UserResource>> response) {
                        if(response.body()!=null) {
                            data.setValue(response.body());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<UserResource>> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }




}
