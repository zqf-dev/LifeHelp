package com.zqf.lifehelp.view.activity.newsdetail;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.utils.customview.NetLoadView;
import com.zqf.lifehelp.utils.widget.X5WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * class from
 * Created by zqf
 * Time 2018/1/8 15:56
 */

public class TencentWebview extends Activity {

    @Bind(R.id.teccent_webview)
    X5WebView teccentWebview;
    @Bind(R.id.act_left_iv)
    ImageView actLeftIv;
    @Bind(R.id.act_left_tv)
    TextView actLeftTv;
    @Bind(R.id.webview_loading)
    NetLoadView webviewLoading;
    private String sourceurl;
    private String sourlocaldefault = "http://192.168.1.109:8080/Demo/demo2-hls-html5.html";
    private static final int MSG_URL = 0x10;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_URL:
                    Logger.e("加载handler消息");
                    webviewLoading.setStatue(NetLoadView.ALL_GONE);
                    webviewLoading.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        try {
            if (Integer.parseInt(Build.VERSION.SDK) >= 11) {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.teccent_webview_layout);
        ButterKnife.bind(this);
        sourceurl = getIntent().getStringExtra("sourceurl");
        initWebView();
    }


    /**
     * 初始化WebView设置
     */
    private void initWebView() {
        actLeftTv.setText("详情");
        webviewLoading.setStatue(NetLoadView.LOADING);
        teccentWebview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                Logger.e("进度--" + webView.getProgress());
                webviewLoading.setVisibility(View.VISIBLE);
                webviewLoading.setStatue(NetLoadView.LOADING);
                mHandler.sendEmptyMessageDelayed(MSG_URL, 1500);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        teccentWebview.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsConfirm(webView, s, s1, jsResult);
            }
        });

        WebSettings webSetting = teccentWebview.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        long time = System.currentTimeMillis();
        Logger.e("加载url");
        if (sourceurl.equals("null") || TextUtils.isEmpty(sourceurl)) {
            teccentWebview.loadUrl(sourlocaldefault);
        } else {
            teccentWebview.loadUrl(sourceurl);
        }
        Logger.e("time-cost-cost time: " + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (teccentWebview != null && teccentWebview.canGoBack()) {
                teccentWebview.goBack();
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (teccentWebview != null) {
            teccentWebview.destroy();
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    @OnClick({R.id.act_left_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_left_iv:
                finish();
                break;
        }
    }
}
