package com.zqf.lifehelp.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.zqf.lifehelp.R;

/**
 * class from 加载中的对话框
 * Created by zqf
 * Time 2018/1/10 15:27
 */

public class LoadingDialog extends Dialog {
    private Context mContext;
    private String mLoadingTip;
    private TextView mLoadingTv;

    public LoadingDialog(Context context, String msg, int style) {
        super(context, style);
        this.mContext = context;
        this.mLoadingTip = msg;
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    //初始化
    private void initView() {
        setContentView(R.layout.base_loading_dialog);
        mLoadingTv = (TextView) findViewById(R.id.loadingTextView);
    }

    public static LoadingDialog show(Context context, String message) {
        LoadingDialog sDialog = new LoadingDialog(context, message, R.style.ProgressHUD);
        sDialog.show();
        return sDialog;
    }
}
