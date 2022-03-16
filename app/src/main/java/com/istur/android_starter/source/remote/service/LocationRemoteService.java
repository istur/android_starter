package com.istur.android_starter.source.remote.service;
import com.istur.android_starter.common.architecture.communication.ApiStateResponse;
import com.istur.android_starter.model.Location;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationRemoteService {

    @GET("location")
    Observable<ApiStateResponse<List<Location>>> getAllLocations();
}

