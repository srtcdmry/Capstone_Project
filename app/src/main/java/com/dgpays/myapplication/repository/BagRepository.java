package com.dgpays.myapplication.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dgpays.myapplication.model.MyBagResources;
import com.dgpays.myapplication.model.ProductResource;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BagRepository {
    private final ProductResourcesInterface apiRequest;

    public BagRepository(Application application) {
        apiRequest = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
    }

    public LiveData<List<ProductResource>> productsResponse() {
        final MutableLiveData<List<ProductResource>> data = new MutableLiveData<>();
        apiRequest.getAllProduct()
                .enqueue(new Callback<List<ProductResource>>() {
                    @Override
                    public void onResponse(Call<List<ProductResource>> call, Response<List<ProductResource>> response) {
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductResource>> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }

    public LiveData<List<MyBagResources>> getBagRepository() {
        final MutableLiveData<List<MyBagResources>> data = new MutableLiveData<>();
        apiRequest.getMyBag()
                .enqueue(new Callback<List<MyBagResources>>() {
                    @Override
                    public void onResponse(Call<List<MyBagResources>> call, Response<List<MyBagResources>> response) {
                        if(response.body()!=null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MyBagResources>> call, Throwable t) {
                        data.setValue(null);
                        System.out.println("DGPAYSSSS  " +  t.getLocalizedMessage());
                    }
                });

        return data;
    }
}