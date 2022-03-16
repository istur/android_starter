package com.istur.android_starter.common.architecture.bundle;

import android.os.Bundle;

public interface BundleBuilderInterface<T> {

    Bundle build(T t);


    T getBundle(Bundle bundle);

}
