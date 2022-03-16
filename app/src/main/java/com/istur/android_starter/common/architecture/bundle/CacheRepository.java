package com.istur.android_starter.common.architecture.bundle;

import android.os.Bundle;

import java.util.HashMap;

public class CacheRepository {

    private static CacheRepository instance;
    private HashMap<String, Bundle> cacheRepository = new HashMap<>();

    public static CacheRepository getInstance(){
        if ( instance == null ){
            instance = new CacheRepository();
        }

        return instance;
    }

    private CacheRepository(){
    }

    public void saveNavigationBundle(Class viewModelClass, Bundle bundle){
        cacheRepository.put(viewModelClass.getName(), bundle);
    }

    public Bundle getNavigationBundle(Class viewModelClass){
        return cacheRepository.get(viewModelClass.getName());
    }

    public void removeNavigationBundle(Class viewModelClass){
        cacheRepository.remove(viewModelClass.getName());
    }

    public boolean containsNavigationBundle(Class viewModelClass) {
        return cacheRepository.containsKey(viewModelClass.getName());
    }


}
