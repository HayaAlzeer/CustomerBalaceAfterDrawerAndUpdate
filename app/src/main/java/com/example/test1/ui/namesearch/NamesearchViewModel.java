package com.example.test1.ui.namesearch;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NamesearchViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NamesearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Name search fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}