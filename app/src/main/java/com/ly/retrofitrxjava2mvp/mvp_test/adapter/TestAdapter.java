package com.ly.retrofitrxjava2mvp.mvp_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ly.retrofitrxjava2mvp.R;
import com.ly.retrofitrxjava2mvp.mvp_test.bean.TestBean;
import com.ly.retrofitrxjava2mvp.utils.GlideApp;

import java.util.List;

/**
 * Create by LiuYang on 2018/7/17 16:57
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.viewHolder> {

    private List<TestBean.StoriesBean> list;
    private Context context;

    public TestAdapter(List<TestBean.StoriesBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        GlideApp.with(context).load(list.get(position).getImages().get(0)).into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());
        holder.textView1.setText("2017-9-7 12:00");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView, textView1;

        viewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
            textView1 = itemView.findViewById(R.id.text1);
        }
    }
}
