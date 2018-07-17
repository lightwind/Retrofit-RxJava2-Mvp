package com.ly.retrofitrxjava2mvp.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.ly.retrofitrxjava2mvp.model.RetrofitService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by LiuYang on 2018/7/17 15:20
 */
public class RetrofitFactory {
    // 访问超时的时间
    private static final long TIME_OUT = 20;

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NonNull String message) {
                    Log.e("TAG", "log: " + message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build();

    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(RetrofitService.BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setLenient()
                    .create()))
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build().create(RetrofitService.class);

    //获得RetrofitService对象
    public static RetrofitService getInstance() {
        return retrofitService;
    }
}
