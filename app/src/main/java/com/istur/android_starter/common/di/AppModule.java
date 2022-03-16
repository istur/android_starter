package com.istur.android_starter.common.di;

import android.app.Application;

import com.istur.android_starter.AStartApplication;
import com.istur.android_starter.source.database.LocationDao;
import com.istur.android_starter.source.database.MyDatabase;
import com.istur.android_starter.source.local.kvcomp.KVComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    Application provideContext(AStartApplication application) {
        return application;
    }

    @Singleton
    @Provides
    protected KVComponent provideKVComponent(Application application) {
        return KVComponent.getKVComponent(application.getApplicationContext());
    }

    @Singleton
    @Provides
    protected MyDatabase provideDatabase(Application application) {
        return MyDatabase.getDatabase(application);
    }

    @Singleton
    @Provides
    LocationDao provideLocationDao(Application application) {
        return MyDatabase.getDatabase(application).locationDao();
    }


}
