package com.istur.android_starter.common.architecture.communication;

import androidx.lifecycle.Observer;

import timber.log.Timber;


public abstract class UiObserver<T> implements Observer<ApiStateResponse<T>> {

    @Override
    public void onChanged(ApiStateResponse<T> apiResponse) {
        switch (apiResponse.getState()){
            case SUCCESS:
                onSuccess(apiResponse.getResponse());
                break;
            case FAIL:
                Timber.e(apiResponse.getThrowable());
                onFail(apiResponse.getThrowable());
                break;
        }
    }

    public abstract void onSuccess(T response);

    public abstract void onFail(Throwable throwable);

}
