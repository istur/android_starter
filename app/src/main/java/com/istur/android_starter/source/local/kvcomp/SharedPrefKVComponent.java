package com.istur.android_starter.source.local.kvcomp;

import android.content.Context;

import com.google.gson.Gson;
import com.istur.android_starter.source.local.kvcomp.secure.SecureStore;

class SharedPrefKVComponent extends KVComponent {

    private SecureStore secureStore;

    SharedPrefKVComponent(Context ctx) {
        secureStore = new SecureStore(ctx);
    }

    @Override
    public String getString(String key, String def) {
        return secureStore.contains(key) ? secureStore.getString(key) : def;
    }

    @Override
    public void putString(String key, String value) {
        secureStore.putString(key, value);
    }

    @Override
    public boolean getBoolean(String key, boolean def) {
        if (secureStore.contains(key)) {
            String value = secureStore.getString(key);
            return Boolean.parseBoolean(value);
        } else return def;
    }

    @Override
    public void putBoolean(String key, boolean value) {
        secureStore.putString(key, String.valueOf(value));
    }

    @Override
    public void remove(String key) {
        secureStore.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return secureStore.contains(key);
    }

    @Override
    public void putObject(String key, Object value) {
        secureStore.putString(key, new Gson().toJson(value));
    }

    @Override
    public <T> T getObject(String key, Class<T> type) {
        return secureStore.contains(key) ? new Gson().fromJson(secureStore.getString(key), type) : null;
    }

    @Override
    public void clear() {
        secureStore.clear();
    }

}