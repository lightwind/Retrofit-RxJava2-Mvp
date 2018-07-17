package com.ly.retrofitrxjava2mvp.model;

import com.ly.retrofitrxjava2mvp.retrofit.BaseApiImpl;

/**
 * 基础网络访问
 * Create by LiuYang on 2018/7/17 16:52
 */
public class Api extends BaseApiImpl {

    private static Api api = new Api(RetrofitService.BASE_URL);

    public Api(String baseUrl) {
        super(baseUrl);
    }

    public static RetrofitService getInstance() {
        return api.getRetrofit().create(RetrofitService.class);
    }
}
