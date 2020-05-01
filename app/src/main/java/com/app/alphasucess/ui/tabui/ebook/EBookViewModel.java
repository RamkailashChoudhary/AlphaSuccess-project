package com.app.alphasucess.ui.tabui.ebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EBookViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EBookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}