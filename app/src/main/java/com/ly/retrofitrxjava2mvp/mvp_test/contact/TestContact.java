package com.ly.retrofitrxjava2mvp.mvp_test.contact;

import com.ly.retrofitrxjava2mvp.mvp_base.BasePresenter;
import com.ly.retrofitrxjava2mvp.mvp_base.BaseView;
import com.ly.retrofitrxjava2mvp.mvp_test.bean.TestBean;

import java.util.List;

/**
 * 连接View和Presenter的接口
 * Create by LiuYang on 2018/7/17 17:07
 */
public interface TestContact {
    interface View extends BaseView {
        /**
         * 设置数据
         */
        void setData(List<TestBean.StoriesBean> dataList);
    }

    interface presenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getData();
    }
}
