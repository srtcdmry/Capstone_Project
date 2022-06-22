package com.dgpays.myapplication.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dgpays.myapplication.model.ProductResource;
import com.dgpays.myapplication.retrofit.ApiRetrofitClient;
import com.dgpays.myapplication.retrofit.ProductResourcesInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository {
    private final ProductResourcesInterface apiRequest;

    public ProductsRepository(Application application) {
        apiRequest = ApiRetrofitClient.getClient().create(ProductResourcesInterface.class);
    }


    public LiveData<List<ProductResource>> productsResponse() {
        final MutableLiveData<List<ProductResource>> data = new MutableLiveData<>();
        apiRequest.getAllProduct()
                .enqueue(new Callback<List<ProductResource>>() {
                    @Override
                    public void onResponse(Call<List<ProductResource>> call, Response<List<ProductResource>> response) {
                        if(response.body()!=null) {
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

    public LiveData<List<ProductResource>> getOneproduct() {
        final MutableLiveData<List<ProductResource>> data = new MutableLiveData<>();
        apiRequest.getOneProducts()
                .enqueue(new Callback<List<ProductResource>>() {
                    @Override
                    public void onResponse(Call<List<ProductResource>> call, Response<List<ProductResource>> response) {
                        if(response.body()!=null) {
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

}
