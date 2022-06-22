package com.dgpays.myapplication.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dgpays.myapplication.dao.UserDao;
import com.dgpays.myapplication.database.AppDatabase;
import com.dgpays.myapplication.model.UserResource;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;

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
                            System.out.println("DGPAYS " + response.code());
                        }
                        System.out.println("DGPAYS null geldi" );

                    }

                    @Override
                    public void onFailure(Call<List<UserResource>> call, Throwable t) {
                        data.setValue(null);
                        System.out.println("DGPAYS " + t.getLocalizedMessage());
                    }
                });

        return data;
    }




}
