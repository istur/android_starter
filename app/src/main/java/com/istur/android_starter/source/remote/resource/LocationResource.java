package com.istur.android_starter.source.remote.resource;

import com.istur.android_starter.common.architecture.communication.ApiStateResponse;
import com.istur.android_starter.model.Location;

import java.util.List;

import io.reactivex.Observable;

public abstract class LocationResource implements AppResource {

    public abstract Observable<ApiStateResponse<List<Location>>> getAllLocations();
}
