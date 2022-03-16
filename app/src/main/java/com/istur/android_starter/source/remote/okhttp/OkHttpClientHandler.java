package com.istur.android_starter.source.remote.okhttp;

import android.util.Log;
import android.webkit.CookieManager;

import com.istur.android_starter.AStartApplication;
import com.istur.android_starter.BuildConfig;
import com.istur.android_starter.source.local.kvcomp.KVComponent;
import com.istur.android_starter.source.remote.RetrofitClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

public class OkHttpClientHandler {

    private static List<Cookie> mCookies = new ArrayList<>();

    public static OkHttpClient getOkHttpClient(String hostUrl, boolean trustAllCerts) {
        try {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            // check if it's requested to trust all certificates(usually for non client owned environment),
            // otherwise setup certificate pinning
            if (trustAllCerts) {
                try {
                    if (SSLHandler.isHTTPS(BuildConfig.APP_HOST_URL)) { // we have to set the SocketFactory only if we are over HTTPS
                        builder.sslSocketFactory(SSLHandler.getSSLContext(SSLHandler.trustAllCerts()).getSocketFactory(), SSLHandler.trustAllCerts());
                        builder.hostnameVerifier(new HostnameVerifier() {
                            @Override
                            public boolean verify(String hostname, SSLSession session) {
                                return true;
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            } else {
                try {
                    URL url = new URL(hostUrl);
                    String host = url.getHost();
//                    // certificate pinning polyfill through TrustKit for Android API < 24
//                    builder.sslSocketFactory(
//                            TrustKit.getInstance().getSSLSocketFactory(host),
//                            TrustKit.getInstance().getTrustManager(host));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(new AddHTTPHeaderInterceptor())
                    .addInterceptor(interceptor)
                    // Handle cookies
//                    .cookieJar(createCookieJar())
                    .connectTimeout(BuildConfig.APP_REMOTE_SERVICE_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(BuildConfig.APP_REMOTE_SERVICE_TIMEOUT, TimeUnit.SECONDS);

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private static boolean isJSessionID(Cookie cookie) {
        if (cookie.name().equals(RetrofitClient.JSESSIONID))
            return true;
        return false;
    }

    private static boolean isJSessionIDAlias(Cookie cookie) {
        if (cookie.name().equals(RetrofitClient.JSESSIONID_ALIAS))
            return true;
        return false;
    }

    private static boolean isLTPAToken(Cookie cookie) {
        if (cookie.name().equals(RetrofitClient.LTPATOKEN))
            return true;
        return false;
    }

    private static boolean isLTPATokenAlias(Cookie cookie) {
        if (cookie.name().equals(RetrofitClient.LTPATOKEN_ALIAS))
            return true;
        return false;
    }

    public List<Cookie> getmCookies() {
        return mCookies;
    }

    public void setmCookies(List<Cookie> mCookies) {
        this.mCookies = mCookies;
    }
}