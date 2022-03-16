package com.istur.android_starter.states.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.istur.android_starter.R;
import com.istur.android_starter.common.architecture.BaseFragment;

public class MainFragment extends BaseFragment<MainViewModel> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    protected Class getViewModelClass() {
        return MainViewModel.class;
    }
}
