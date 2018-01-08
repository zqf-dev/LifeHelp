package com.zqf.lifehelp.view.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.view.customview.SlideShowView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * class from 菜谱大全的RecycleView适配器
 * Created by zqf
 * Time 2017/6/27 10:38
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int RECIPE_TYPE1 = 0x011;//广告
    private static final int RECIPE_TYPE2 = 0x012;//类别
    private static final int RECIPE_TYPE3 = 0x013;//精选
    private Context mContext;
    private String[] images_url;

    public RecipesAdapter(Context context, String[] images_url) {
        this.mContext = context;
        this.images_url = images_url;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case RECIPE_TYPE1:
                return new VHolder1(LayoutInflater.from(mContext).inflate(R.layout.recipe_recyle_item1, parent, false));
            case RECIPE_TYPE2:
                return new VHolder2(LayoutInflater.from(mContext).inflate(R.layout.recipe_recyle_item2, parent, false));
            case RECIPE_TYPE3:
                return new VHolder3(LayoutInflater.from(mContext).inflate(R.layout.recipe_recyle_item3, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHolder1) {
            bindType1((VHolder1) holder, position);
        } else if (holder instanceof VHolder2) {
            bindType2((VHolder2) holder, position);
        } else if (holder instanceof VHolder3) {
            bindType3((VHolder3) holder, position);
        }
    }

    /**
     * 精选
     *
     * @param holder-->
     * @param position-->
     */
    private void bindType3(VHolder3 holder, int position) {

    }

    /**
     * 类别
     *
     * @param holder-->
     * @param position-->
     */
    private void bindType2(VHolder2 holder, int position) {
        GridLayoutManager manager = new GridLayoutManager(mContext, 4,
                GridLayoutManager.HORIZONTAL, false);
        holder.sort_recycle.setLayoutManager(manager);
        holder.sort_recycle.setAdapter(new SortRecyAdapter(mContext));
    }

    /**
     * 广告类型
     *
     * @param holder-->
     * @param position-->
     */
    private void bindType1(VHolder1 holder, int position) {
        holder.mShowView.setImageUrls(images_url);
        holder.mShowView.setTag(position);
        holder.mShowView.setOnGolistener(imagelistener);
    }


    @Override
    public int getItemCount() {
        return 3;
    }


    private View.OnClickListener imagelistener;

    public void setImageListener(View.OnClickListener imagelistener) {
        this.imagelistener = imagelistener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return RECIPE_TYPE1;
        } else if (position == 1) {
            return RECIPE_TYPE2;
        } else if (position == 2) {
            return RECIPE_TYPE3;
        }
        return -1;
    }

    public class VHolder1 extends RecyclerView.ViewHolder {
        @Bind(R.id.slideShowView)
        SlideShowView mShowView;

        public VHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    public class VHolder2 extends RecyclerView.ViewHolder {
        @Bind(R.id.sort_recycle)
        RecyclerView sort_recycle;

        public VHolder2(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    public class VHolder3 extends RecyclerView.ViewHolder {
        @Bind(R.id.menu_image)
        ImageView menuImage;
        @Bind(R.id.menu_name)
        TextView menuName;

        public VHolder3(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
