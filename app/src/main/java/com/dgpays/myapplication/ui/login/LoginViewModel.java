package com.dgpays.myapplication.ui.login;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dgpays.myapplication.database.AppDatabase;
import com.dgpays.myapplication.model.UserResource;
import com.dgpays.myapplication.repository.UserRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<UserResource>> userResourceLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        this.userResourceLiveData = userRepository.userResponse();
    }

    public LiveData<List<UserResource>> getUserResourceLiveData() {
        return userResourceLiveData;
    }

}



