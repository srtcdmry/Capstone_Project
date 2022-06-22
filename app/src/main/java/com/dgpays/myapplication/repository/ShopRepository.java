package com.dgpays.myapplication.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepository {
    private final ProductResourcesInterface apiRequest;


    public ShopRepository(Application application) {
        apiRequest = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
    }


    public LiveData<List<String>> categoriesResponse(){
        final MutableLiveData<List<String>> data = new MutableLiveData<>();
        apiRequest.getCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
