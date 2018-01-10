/**
 *
 */
package com.zqf.lifehelp.utils;

import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 辅助工具类
 *
 * @项目名称： AMapLocationDemo2.x
 * @文件名称: Utils.java
 * @类型名称: Utils
 */
public class MapLocationUtil {

    /**
     * 默认的定位参数
     */
    public static AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setHttpTimeOut(10000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
//        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }


    /**
     * 根据定位结果返回定位信息的字符串
     * errCode等于0代表定位成功，其他的为定位失败
     * 具体的可以参照官网定位错误码说明
     */
    public synchronized static String getLocationStr(AMapLocation location) {
        if (null == location) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (location.getErrorCode() == 0) {
            sb.append(location.getProvince() + ",");
            sb.append(location.getCity());
        } else {
            //定位失败
            sb.append("定位失败" + "\n");
            sb.append("错误码:" + location.getErrorCode() + "\n");
            sb.append("错误信息:" + location.getErrorInfo() + "\n");
            sb.append("错误描述:" + location.getLocationDetail() + "\n");
            sb.append("回调时间: " + formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
        }
        return sb.toString();
    }


    private static SimpleDateFormat sdf = null;

    public static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }
}
