package com.zqf.lifehelp.factory.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Created by zqf on 2018/7/2.
 * 新的BaseActivity
 */

public abstract class NBaseActivity extends AppCompatActivity {

    //沉浸式框架
    protected ImmersionBar mImmersionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        NinitView();
        initView();
    }

    //初始化
    private void NinitView() {
        //所有子类都将继承这些相同的属性
        mImmersionBar = ImmersionBar.with(this);
        //解决软键盘与底部输入框冲突问题
        mImmersionBar.keyboardEnable(true);
        //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，
        //如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarDarkFont(true, 0.2f);
        mImmersionBar.init();
    }

    //获取子类的View
    protected abstract int getLayout();

    //子类初始化
    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变
        // 在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }
}
