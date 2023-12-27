package com.example.test1.ui.closeApp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CloseAppViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CloseAppViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is closeApp fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}