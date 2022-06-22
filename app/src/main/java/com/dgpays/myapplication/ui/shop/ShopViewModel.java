package com.dgpays.myapplication.ui.shop;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dgpays.myapplication.repository.ShopRepository;

import java.util.List;

public class ShopViewModel extends AndroidViewModel {

    private ShopRepository shopRepository;
    private LiveData<List<String>> categoriesLiveData;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        shopRepository = new ShopRepository(application);
        this.categoriesLiveData = shopRepository.categoriesResponse();

    }

    public LiveData<List<String>> getCategoriesLiveData() {
        return categoriesLiveData;
    }
}