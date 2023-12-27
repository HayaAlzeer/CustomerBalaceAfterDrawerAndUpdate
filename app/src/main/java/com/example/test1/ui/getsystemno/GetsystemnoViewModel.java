package com.example.test1.ui.getsystemno;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GetsystemnoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GetsystemnoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}