package com.ly.retrofitrxjava2mvp.mvp_test.presenter;

import com.ly.retrofitrxjava2mvp.model.Api;
import com.ly.retrofitrxjava2mvp.mvp_base.baseImpl.BasePresenterImpl;
import com.ly.retrofitrxjava2mvp.mvp_test.bean.TestBean;
import com.ly.retrofitrxjava2mvp.mvp_test.contact.TestContact;
import com.ly.retrofitrxjava2mvp.retrofit.ExceptionHelper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by LiuYang on 2018/7/17 17:08
 */
public class TestPresenter extends BasePresenterImpl<TestContact.View> implements TestContact.presenter {

    public TestPresenter(TestContact.View view) {
        super(view);
    }

    @Override
    public void getData() {
        Api.getInstance().test()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        mView.showLoadingDialog("");
                    }
                })
                .map(new Function<TestBean, List<TestBean.StoriesBean>>() {
                    @Override
                    public List<TestBean.StoriesBean> apply(TestBean testBean) throws Exception {
                        return testBean.getStories();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TestBean.StoriesBean>>() {
                    @Override
                    public void accept(List<TestBean.StoriesBean> storiesBeans) throws Exception {
                        mView.dismissLoadingDialog();
                        mView.setData(storiesBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.dismissLoadingDialog();
                        ExceptionHelper.handleException(throwable);
                    }
                });
    }
}
