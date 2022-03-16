package com.istur.android_starter.source.remote.resource.impl;

import com.istur.android_starter.common.architecture.communication.ApiStateResponse;
import com.istur.android_starter.model.Location;
import com.istur.android_starter.source.remote.resource.LocationResource;
import com.istur.android_starter.source.remote.service.LocationRemoteService;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class LocationResourceImpl extends LocationResource {

    LocationRemoteService locationRemoteService;

    public LocationResourceImpl(Retrofit retrofit) {
        locationRemoteService = retrofit.create(LocationRemoteService.class);
    }

    @Override
    public Observable<ApiStateResponse<List<Location>>> getAllLocations() {
        return locationRemoteService.getAllLocations();
    }
}
