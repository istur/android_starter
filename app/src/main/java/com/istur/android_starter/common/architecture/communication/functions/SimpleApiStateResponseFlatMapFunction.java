package com.istur.android_starter.common.architecture.communication.functions;

import com.istur.android_starter.common.architecture.communication.ApiStateResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public abstract class SimpleApiStateResponseFlatMapFunction<T, R> implements Function<ApiStateResponse<T>, ObservableSource<ApiStateResponse<R>>> {

    @Override
    public ObservableSource<ApiStateResponse<R>> apply(ApiStateResponse<T> apiStateResponse) throws Exception {
        if(apiStateResponse.isLoading()) {
            return Observable.just(ApiStateResponse.loading());
        }
        if(apiStateResponse.isSuccess()) {
            return Observable.just(
              ApiStateResponse.success(
                      flatMapOnSuccess(apiStateResponse.getResponse())
              )
            );
        }
        if(apiStateResponse.isFail()) {
            return Observable.just(ApiStateResponse.fail(apiStateResponse.getThrowable()));
        }
        return null;
    }

    public abstract R flatMapOnSuccess(T data);
}
