package com.ly.retrofitrxjava2mvp.model;

import com.ly.retrofitrxjava2mvp.mvp_test.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Create by LiuYang on 2018/7/17 15:19
 */
public interface RetrofitService {
    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<TestBean> test();
}
