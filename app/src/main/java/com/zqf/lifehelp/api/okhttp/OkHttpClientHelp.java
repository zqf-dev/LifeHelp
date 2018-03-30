package com.zqf.lifehelp.api.okhttp;

import android.content.Context;
import android.text.TextUtils;

import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.api.Interceptor.LoggerInterceptor;
import com.zqf.lifehelp.api.Interceptor.Platform;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * class from OkHttpClientHelp帮助类
 * Created by zqf
 * Time 2018/1/2 15:31
 */

public class OkHttpClientHelp {
    private Context mContext;

    public OkHttpClientHelp(Context context) {
        this.mContext = context;
    }

    /**
     * 创建OkHttpClient及其基本配置
     */
    public OkHttpClient getOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addNetworkInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.HEADERS))//添加Head
                .connectTimeout(20 * 1000L, TimeUnit.SECONDS)//连接时间
                .readTimeout(20 * 1000L, TimeUnit.SECONDS)//读取时间
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return false;
                    }
                })
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder request = chain.request().newBuilder();
                        //统一设置User-Agent
                        Headers.Builder headbuilder = new Headers.Builder();
                        headbuilder.removeAll("User-Agent");
                        headbuilder.add("User-Agent", Platform.getUserAgent(mContext));
                        //添加判断具体请求来自哪？
                        request.headers(headbuilder.build());
                        //拦截请求重新设置Request添加Head信息
                        String token = App.getSp().getString("request_head_token");
                        String keyid = App.getSp().getString("request_head_keyId");
                        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(keyid)) {
                            request.addHeader("request_head_token", token)
                                    .addHeader("request_head_keyId", keyid);
                        }
                        return chain.proceed(request.build());
                    }
                })
                .addInterceptor(new LoggerInterceptor("OkHttpHelp"))//打印的日志
                .build();
        return okHttpClient;
    }
}
