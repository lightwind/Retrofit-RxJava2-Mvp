package com.ly.retrofitrxjava2mvp.mvp_test.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ly.retrofitrxjava2mvp.R;
import com.ly.retrofitrxjava2mvp.mvp_base.baseImpl.BaseActivity;
import com.ly.retrofitrxjava2mvp.mvp_test.adapter.TestAdapter;
import com.ly.retrofitrxjava2mvp.mvp_test.bean.TestBean;
import com.ly.retrofitrxjava2mvp.mvp_test.contact.TestContact;
import com.ly.retrofitrxjava2mvp.mvp_test.presenter.TestPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<TestContact.presenter> implements TestContact.View {

    private List<TestBean.StoriesBean> mStoriesBeanList = new ArrayList<>();
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mPresenter.getData();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TestAdapter(mStoriesBeanList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected TestContact.presenter initPresenter() {
        return new TestPresenter(this);
    }

    @Override
    public void setData(List<TestBean.StoriesBean> dataList) {
        mStoriesBeanList.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }
}
