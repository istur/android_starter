package com.istur.android_starter.common.architecture.communication.functions;

import com.istur.android_starter.common.architecture.communication.ApiStateResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class ApiStateResponseRoomWrapper<T> implements Function<T, ObservableSource<ApiStateResponse<T>>> {

    @Override
    public ObservableSource<ApiStateResponse<T>> apply(T data) throws Exception {
        return Observable.just(
                ApiStateResponse.success(data)
        );
    }

    public static <T> Observable<ApiStateResponse<T>> wrap(Observable<T> observable) {
        return observable.flatMap(new ApiStateResponseRoomWrapper<T>());
    }
}
