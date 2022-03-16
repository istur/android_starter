package com.istur.android_starter.source.remote;

import com.google.gson.Gson;
import com.istur.android_starter.BuildConfig;
import com.istur.android_starter.source.remote.okhttp.OkHttpClientHandler;
import com.istur.android_starter.source.remote.retrofitcalladapter.RxJava2CallAdapterFactory;
import com.istur.android_starter.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class RetrofitClient {

    public static final String JSESSIONID = "JSESSIONID";
    // alias used to hide to JSESSIONID key to an attacker looking through device saved cookies
    public static final String JSESSIONID_ALIAS = "xtE9Zm?oUr7";
    public static final String LTPATOKEN = "LtpaToken2";
    public static final String PICO_TOKEN = "Pico-Token";
    // alias used to hide to LTPATOKEN key to an attacker looking through device saved cookies
    public static final String LTPATOKEN_ALIAS = "3J4Fga,gpr}t";

    private static Retrofit mRetrofit;
    private static Retrofit mRetrofitGetAddress;
    private static Retrofit mRetrofitWithApplicationType;
    public static final String BASE_URL = BuildConfig.APP_API_URL; //"https://ticketsuat.c2c-online.co.uk";//"https://ticketsuat.c2c-online.co.uk"; //"https://172.25.220.11"; //"https://10.0.2.2:9443";//"https://172.25.220.12";
    public String applicationType = "json";

    public static MockRetrofit getMockRetrofitInstance() {
        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setDelay(2, TimeUnit.SECONDS);
        networkBehavior.setVariancePercent(40);
        networkBehavior.setErrorPercent(2);

        return new MockRetrofit.Builder(getRetrofitInstance())
                .networkBehavior(networkBehavior)
                .build();
    }

    public static Retrofit getRetrofitInstance() {

        // da aggiungere se si vuole usare un converter con delle regole per deserializzare
//        Gson gson = GsonConverter.getGsonBuilder();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new Gson());

        if (mRetrofit == null) {

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // REQUIRED
                    .client(getHTTPClient(BASE_URL)) // VERY VERY IMPORTANT
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                    .build(); // REQUIRED
        }

        return mRetrofit;
    }

    /**
     * IT initializes an OKHTTPClient that trust all certificates only if we are not in certificazione, correttiva or produzione
     *
     * @param
     * @return
     */
    private static OkHttpClient getHTTPClient(String hostUrl) {
        if (BuildConfig.APP_ENVIRONMENT.equalsIgnoreCase(Constants.ENVIRONMENT_PROD)) {
            return OkHttpClientHandler.getOkHttpClient(hostUrl, false);
        }
        return OkHttpClientHandler.getOkHttpClient(hostUrl, true);
    }
}


