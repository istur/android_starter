package com.istur.android_starter.common.di;

import com.istur.android_starter.AStartApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ViewModule.class,
        ViewModelModule.class
})
public interface ApplicationGraph extends AndroidInjector<AStartApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AStartApplication> {
    }
}
