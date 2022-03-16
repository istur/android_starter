package com.istur.android_starter.states.main;

import android.app.Application;

import androidx.annotation.NonNull;

import com.istur.android_starter.common.architecture.BaseViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
