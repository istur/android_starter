package com.istur.android_starter.states.main;

import androidx.fragment.app.Fragment;

import com.istur.android_starter.common.architecture.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected Fragment getMainContentFragment() {
        return new MainFragment();
    }
}
