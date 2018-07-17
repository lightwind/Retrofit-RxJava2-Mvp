package com.ly.retrofitrxjava2mvp.retrofit;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.ly.retrofitrxjava2mvp.utils.LogUtils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Create by LiuYang on 2018/7/17 16:12
 */
public class BaseApiImpl implements BaseApi {

    private volatile static Retrofit retrofit = null;
    private Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder();
    private OkHttpClient.Builder mHttpClientBuilder = new OkHttpClient.Builder();

    public BaseApiImpl(String baseUrl) {
        mRetrofitBuilder.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mHttpClientBuilder.addInterceptor(getLoggerInterceptor()).build())
                .baseUrl(baseUrl);
    }

    /**
     * 构建Retrofit
     */
    @Override
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            // 锁定代码块
            synchronized (BaseApiImpl.class) {
                if (retrofit == null) {
                    retrofit = mRetrofitBuilder.build();
                }
            }
        }
        return retrofit;
    }

    @Override
    public OkHttpClient.Builder setInterceptor(Interceptor interceptor) {
        return mHttpClientBuilder.addInterceptor(interceptor);
    }

    @Override
    public Retrofit.Builder setConverterFactory(Converter.Factory factory) {
        return mRetrofitBuilder.addConverterFactory(factory);
    }

    /**
     * 日志拦截器
     * 将你访问的接口信息
     *
     * @return 拦截器
     */
    private HttpLoggingInterceptor getLoggerInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                LogUtils.d("ApiUrl ---> " + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
