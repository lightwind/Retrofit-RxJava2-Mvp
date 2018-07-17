package com.ly.retrofitrxjava2mvp.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Retrofit实现基本接口
 * Create by LiuYang on 2018/7/17 16:11
 */
public interface BaseApi {

    Retrofit getRetrofit();

    OkHttpClient.Builder setInterceptor(Interceptor interceptor);

    Retrofit.Builder setConverterFactory(Converter.Factory factory);

}
