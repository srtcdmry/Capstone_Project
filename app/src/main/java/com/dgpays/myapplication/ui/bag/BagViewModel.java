package com.dgpays.myapplication.ui.bag;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dgpays.myapplication.model.MyBagResources;
import com.dgpays.myapplication.model.ProductResource;
import com.dgpays.myapplication.repository.BagRepository;
import com.dgpays.myapplication.repository.ProductsRepository;

import java.util.List;

public class BagViewModel extends AndroidViewModel {

    private BagRepository bagRepository;
    private LiveData<List<MyBagResources>> myBagResourceLiveData;
    private LiveData<List<ProductResource>> productsResourceLiveData;

    public BagViewModel(@NonNull Application application) {
        super(application);
        bagRepository = new BagRepository(application);
        this.myBagResourceLiveData = bagRepository.getBagRepository();
        this.productsResourceLiveData = bagRepository.productsResponse();
    }

    public LiveData<List<MyBagResources>> myBagResourceLiveData(){
        return myBagResourceLiveData;
    }

    public LiveData<List<ProductResource>> getOneProductResourceLiveData(){
        return productsResourceLiveData;
    }
}