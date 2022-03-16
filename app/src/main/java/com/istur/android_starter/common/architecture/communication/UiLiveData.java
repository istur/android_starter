package com.istur.android_starter.common.architecture.communication;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class UiLiveData<T> extends MediatorLiveData<UiResponse<T>> {

    public Observer<UiResponse<T>> observe(LifecycleOwner owner, UiCallback<T> c) {
        Observer<UiResponse<T>> uiResponseObserver = tUiResponse -> {
            switch (tUiResponse.getUiState()) {
                case RenderData:
                    c.innerRenderData(tUiResponse.getResult());
                    break;
                case RenderError:
                    c.innerRenderError(tUiResponse.getException());
                    break;
                case Loading:
                    c.loading(tUiResponse.getShowLoading());
                    break;
            }
        };
        observe(owner, uiResponseObserver);
        return uiResponseObserver;
    }


}
