package com.huce.t25film.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String BASE_URL = "https://t25film.000webhostapp.com/api/";
    private static final long CACHE_SIZE = 10 * 1024 * 1024; //10mb cache
    private static final int READ_TIMEOUT = 5000;
    private static final int WRITE_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;
    private static String CACHE_CONTROL = "Cache-Control";
    private static final String TIME_CACHE_ONLINE = "public, max-age = 60";// lay du lieu 1 phut truoc neu online
    private static final String TIME_CACHE_OFFLINE = "public, only-if-cached, max-stale = 86400";// lấy dữ liệu 1 ngay truoc neu offline

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private static OkHttpClient initClient(final Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .cache(new Cache(context.getCacheDir(), CACHE_SIZE))
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (isNetworkAvailable(context)) {
                        request = request
                                .newBuilder()
                                .header(CACHE_CONTROL, TIME_CACHE_ONLINE)
                                .build();
                    } else {
                        request = request
                                .newBuilder()
                                .header(CACHE_CONTROL, TIME_CACHE_OFFLINE)
                                .build();
                    }
                    HttpUrl httpUrl = request.url()
                            .newBuilder()
//                            .addQueryParameter(QUERRY_PARAMETER_API_KEY, API_KEY)
                            .build();
                    Request.Builder requestBuilder = request
                            .newBuilder()
                            .url(httpUrl);
                    return chain.proceed(requestBuilder.build());
                });
        return builder.build();
    }

    public static Retrofit buildRetrofit() {
        Retrofit  sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //Add OkHttpClient
//                    .client(initClient(context))
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return sRetrofit;
    }
}
