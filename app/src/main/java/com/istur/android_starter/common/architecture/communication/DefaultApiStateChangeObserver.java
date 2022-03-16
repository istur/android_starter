package com.istur.android_starter.common.architecture.communication;

import io.reactivex.observers.DisposableObserver;

public class DefaultApiStateChangeObserver<T> extends DisposableObserver<ApiStateResponse<T>> {

    private boolean showLoading;
    private UiLiveData<T> mUiLiveData;

    public DefaultApiStateChangeObserver(UiLiveData<T> uiLiveData, boolean showLoading) {
        mUiLiveData = uiLiveData;
        this.showLoading = showLoading;
    }

    public DefaultApiStateChangeObserver(UiLiveData<T> uiLiveData) {
        mUiLiveData = uiLiveData;
        this.showLoading = true;
    }

    public void onApiStateChanged(ApiStateResponse<T> state) {
        switch (state.getState()) {
            case SUCCESS:
                mUiLiveData.setValue(new UiResponse(state.getResponse()));
                break;
            case FAIL:
                mUiLiveData.setValue(new UiResponse(state.getThrowable()));
                break;
            case LOADING:
                mUiLiveData.setValue(new UiResponse(showLoading));
                break;
            case UNINITIALIZED:
                break;
            default:
                break;
        }
    }

    @Override
    public void onNext(ApiStateResponse<T> state) {
        if (!isDisposed())
            onApiStateChanged(state);
    }

    @Override
    public void onError(Throwable e) {
        if (!isDisposed())
            onApiStateChanged(ApiStateResponse.fail(e));
    }

    @Override
    public void onComplete() {
        mUiLiveData = null;
    }


}
