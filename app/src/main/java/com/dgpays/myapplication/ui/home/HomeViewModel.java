package com.dgpays.myapplication.ui.home;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dgpays.myapplication.model.MyBagResources;
import com.dgpays.myapplication.model.ProductResource;
import com.dgpays.myapplication.repository.ProductsRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private ProductsRepository productsRepository;
    private LiveData<List<ProductResource>> productResourceLiveData;
    private LiveData<List<ProductResource>> productsOneResourceLiveData;



    public HomeViewModel(@NonNull Application application) {
        super(application);
        productsRepository = new ProductsRepository(application);
        this.productResourceLiveData = productsRepository.productsResponse();
        this.productsOneResourceLiveData = productsRepository.getOneproduct();
    }

    public LiveData<List<ProductResource>> getProductResourceLiveData(){
        return productResourceLiveData;
    }

    public LiveData<List<ProductResource>> getOneProductResourceLiveData(){
        return productsOneResourceLiveData;
    }
}