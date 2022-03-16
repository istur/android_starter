package com.istur.android_starter.source.remote.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This interceptor put add the relevant Headers at any HTTP requests.
 */
public class AddHTTPHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader("Accept", "application/hh+json, application/json;q=0.9");
        builder.addHeader("Content-Type", "application/json");
        Response proceed = chain.proceed(builder.build());

        // here we can take response information, such as header or other things

        return proceed;
    }
}
