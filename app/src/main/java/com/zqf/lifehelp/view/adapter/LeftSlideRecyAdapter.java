package com.zqf.lifehelp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.model.LeftTagModel;
import com.zqf.lifehelp.utils.customview.recycler.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * class from
 * Created by zqf
 * Time 2017/6/15 11:50
 */

public class LeftSlideRecyAdapter extends RecyclerView.Adapter<LeftSlideRecyAdapter.LeftViewHolder> {
    private List<LeftTagModel> list;

    public LeftSlideRecyAdapter(List<LeftTagModel> list) {
        this.list = list;
    }

    @Override
    public LeftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(App.getCon()).inflate(R.layout.left_recy_item_layout, parent, false);
        return new LeftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeftViewHolder holder, int position) {
        holder.leftRecyTvName.setText(list.get(position).getTag_name());
        holder.left_recy_linear.setTag(position);
        holder.left_recy_linear.setOnClickListener(mListener);
    }

    private View.OnClickListener mListener;

    public void setListener(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class LeftViewHolder extends ViewHolder {
        @Bind(R.id.left_recy_iv_imag)
        ImageView leftRecyIvImag;
        @Bind(R.id.left_recy_tv_name)
        TextView leftRecyTvName;
        @Bind(R.id.left_recy_linear)
        LinearLayout left_recy_linear;

        public LeftViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
