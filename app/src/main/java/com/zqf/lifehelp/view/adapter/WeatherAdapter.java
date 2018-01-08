package com.zqf.lifehelp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * class from 天气的RecycleView适配器
 * Created by zqf
 * Time 2017/6/16 11:09
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_TITLE = 0xff01;//Title+head天气状况类型;
    public static final int TYPE_MOREWEATHER = 0xff02;//五天内天气状况类型;横向滑动RecycleView

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            return new ViewHolder1(LayoutInflater.from(App.getCon()).inflate(R.layout.weather_recy_item_type1, parent, false));
        } else if (viewType == TYPE_MOREWEATHER) {
            return new ViewHolder2(LayoutInflater.from(App.getCon()).inflate(R.layout.weather_recy_item_type2, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            BindType1((ViewHolder1) holder, position);
        } else if (holder instanceof ViewHolder2) {
            BindType2((ViewHolder2) holder, position);
        }
    }

    /**
     * @param holder--ViewHolder1
     * @param position--位置
     */
    private void BindType1(ViewHolder1 holder, int position) {
        
    }

    /**
     * @param holder--ViewHolder2
     * @param position--位置
     */
    private void BindType2(ViewHolder2 holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == 0) {
            type = TYPE_TITLE;
        } else {
            type = TYPE_MOREWEATHER;
        }
        return type;
    }

    @OnClick(R.id.back_image)
    public void onViewClicked() {

    }

    //title的viewholder
    public class ViewHolder1 extends RecyclerView.ViewHolder {
        @Bind(R.id.back_image)
        ImageView backImage;
        @Bind(R.id.temp_tv)
        TextView tempTv;
        @Bind(R.id.kongqi_zl_tv1)
        TextView kongqiZlTv1;

        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    //多天预报类型的viewholder
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        RecyclerView fiveday_recycle;

        public ViewHolder2(View itemView) {
            super(itemView);
            fiveday_recycle = (RecyclerView) itemView.findViewById(R.id.five_day_weather_recycle);
        }
    }
}
