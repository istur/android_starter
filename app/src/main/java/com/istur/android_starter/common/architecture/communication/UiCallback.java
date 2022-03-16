package com.istur.android_starter.common.architecture.communication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.istur.android_starter.AStartApplication;

import retrofit2.HttpException;

public abstract class UiCallback<T> implements LifecycleObserver, LifecycleEventObserver {

    FragmentActivity mActivity;
    LifecycleOwner mOwner;
    boolean showLoading = false;

    public UiCallback(LifecycleOwner owner, FragmentActivity activity) {
        mActivity = activity;
        mOwner = owner;
        mOwner.getLifecycle().addObserver(this);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            mActivity = null;
            mOwner.getLifecycle().removeObserver(this);
        }
    }

    public abstract void renderData(T data);

    public void innerRenderData(T data) {
        renderData(data);
        stopLoading();
    }

    protected void stopLoading() {
        if (showLoading) {
            // stopLoading()
        }
    }

    public void innerRenderError(Throwable t) {
        if (t instanceof HttpException && (((HttpException) t).code() == 401 || ((HttpException) t).code() == 410)) {
            manageGenericError(t);
        } else {
            renderError(t);
        }
        stopLoading();
    }

    public void renderError(Throwable t) {
        manageGenericError(t);
    }

    protected void manageGenericError(Throwable t) {
        if (showLoading) {
//            C2CHandheldApplication.stopLoading();
//        ErrorManager.getErrorManager().showError(mActivity, t);
        }
    }

    public void loading(Boolean b) {
        showLoading = b;
        if (showLoading) {
//            C2CHandheldApplication.startLoading();
        }
    }
}