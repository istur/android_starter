package com.istur.android_starter.common.architecture.bundle;

import android.os.Bundle;


public abstract class MyBundleBuilder<T extends MyBundle> implements BundleBuilderInterface<T> {

    public static String BUNDLE_KEY = "BUNDLE_KEY";

    @Override
    public Bundle build(T t) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY, t);
        return bundle;
    }

    @Override
    public T getBundle(Bundle bundle) {
        return (T) bundle.getSerializable(BUNDLE_KEY);
    }
}
