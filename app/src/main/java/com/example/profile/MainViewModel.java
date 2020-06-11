package com.example.profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MainViewModel() {
    }

    MutableLiveData<String> catId = new MutableLiveData<String>();

    void setCategoryID(String address) {
        catId.setValue(address);
    }
}
