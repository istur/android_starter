package com.istur.android_starter.source.local.kvcomp;

import android.content.Context;

public abstract class KVComponent {

    private static KVComponent instance;

    public static KVComponent getKVComponent(Context ctx) {
        if (instance == null)
            synchronized (KVComponent.class) {
                instance = new SharedPrefKVComponent(ctx);
            }
        return instance;
    }

    public abstract String getString(String key, String def);

    public abstract void putString(String key, String value);

    public abstract boolean getBoolean(String key, boolean def);

    public abstract void putBoolean(String key, boolean value);

    public abstract void remove(String key);

    public abstract boolean contains(String key);

    public abstract void putObject(String key, Object value);

    public abstract <T> T getObject(String key, Class<T> type);

    public abstract void clear();
}