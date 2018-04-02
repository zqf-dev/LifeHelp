package com.zqf.lifehelp.utils.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zqf.lifehelp.R;

/**
 * class from
 * Created by zqf
 * Time 2017/11/2 10:12
 */

public class NetLoadView extends LinearLayout implements View.OnClickListener {
    //显示的加载中、失败、重试等布局view
    private View mView;
    public static final int LOADING = 0;//加载中...
    public static final int STOP_LOADING = 1;//停止加载...
    public static final int NO_DATA = 2;//无数据...
    public static final int NO_NETWORK = 3;//无网络...
    public static final int ALL_GONE = 4;//不显示
    private RelativeLayout mRlError;
    private LinearLayout mLlLoading;
    private Button mTv_error_load;
    private TextView mTv_loading;
    private RelativeLayout mRl_no_data;
    private LoadingView mLoadingView;

    public NetLoadView(Context context) {
        super(context);
        init(context);
    }

    public NetLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NetLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.loading_layout, this);
        //加载中动画ImageView
//        ImageView imageView = (ImageView) mView.findViewById(R.id.iv_loading);
        mLoadingView = (LoadingView) mView.findViewById(R.id.iv_loading);
        //加载中...
        mLlLoading = (LinearLayout) mView.findViewById(R.id.ll_loading);
        //失败
        mRlError = (RelativeLayout) mView.findViewById(R.id.rl_error);
        //无数据
        mRl_no_data = (RelativeLayout) mView.findViewById(R.id.rl_no_data);

        //显示加载中...的TextView
        mTv_loading = (TextView) mView.findViewById(R.id.tv_loading);

        mTv_error_load = (Button) mView.findViewById(R.id.tv_error_load);
        mTv_error_load.setOnClickListener(this);
        //设置显示状态
        setStatue(ALL_GONE);
    }

    public void setStatue(int status) {
        try {
            if (status == LOADING) {
                mRlError.setVisibility(View.GONE);
                mRl_no_data.setVisibility(View.GONE);
                mLlLoading.setVisibility(VISIBLE);

                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingView.start();
            } else if (status == STOP_LOADING) {
                mRlError.setVisibility(View.GONE);
                mLlLoading.setVisibility(View.GONE);
                mRl_no_data.setVisibility(View.GONE);

                mLoadingView.setVisibility(View.GONE);
                mLoadingView.stop();
            } else if (status == NO_NETWORK) {
                mRlError.setVisibility(View.VISIBLE);
                mLlLoading.setVisibility(View.GONE);
                mRl_no_data.setVisibility(View.GONE);

                mLoadingView.setVisibility(View.GONE);
                mLoadingView.stop();
            } else if (status == NO_DATA) {
                mRl_no_data.setVisibility(View.VISIBLE);
                mRlError.setVisibility(View.GONE);
                mLlLoading.setVisibility(View.GONE);

                mLoadingView.setVisibility(View.GONE);
                mLoadingView.stop();
            } else if (status == ALL_GONE) {
                mRl_no_data.setVisibility(View.GONE);
                mRlError.setVisibility(View.GONE);
                mLlLoading.setVisibility(View.GONE);

                mLoadingView.setVisibility(View.GONE);
                mLoadingView.stop();
            } else {
                setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //点击重新加载
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_error_load:
                mListener.refresh();
                setStatue(LOADING);
                break;
        }
    }

    public interface onMyRefreshListener {
        void refresh();
    }

    private onMyRefreshListener mListener;

    public void setMyRefreshListener(onMyRefreshListener mListener) {
        this.mListener = mListener;
    }
}
