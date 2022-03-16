package com.istur.android_starter.common.di;

import com.istur.android_starter.states.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ViewModule {
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();
}
