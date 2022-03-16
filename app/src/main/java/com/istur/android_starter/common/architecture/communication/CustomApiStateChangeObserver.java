package com.istur.android_starter.common.architecture.communication;

import io.reactivex.observers.DisposableObserver;

public abstract class CustomApiStateChangeObserver<T> extends DisposableObserver<ApiStateResponse<T>> {

    public abstract void onApiStateChanged(ApiStateResponse<T> state);

    @Override
    public void onNext(ApiStateResponse<T> state) {
        onApiStateChanged(state);
    }

    @Override
    public void onError(Throwable e) {
        onApiStateChanged(ApiStateResponse.fail(e));
    }

    @Override
    public void onComplete() { }


}
