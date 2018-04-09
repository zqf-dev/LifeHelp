package com.zqf.lifehelp.utils.errorcode;

import com.blankj.utilcode.util.ToastUtils;

/**
 * class from
 * Created by zqf
 * Time 2018/4/9 11:02
 */

public class ErrorCodeUtil {
    //    错误码	说明
//    10001	appkey不合法
//    10020	接口维护
//    10021	接口停用
//    200	成功
    public static void CommonErrorType(String error_code) {
        switch (error_code) {
            case "10010":
                ToastUtils.showShort("AppKey不合法");
                break;
            case "10020":
                ToastUtils.showShort("接口维护");
                break;
            case "10021":
                ToastUtils.showShort("接口停用");
                break;
        }
    }

    public static boolean QueryIDErrorType(String error_code) {
        if (error_code.equals("20601")) {
            return false;
        } else if (error_code.equals("20602")) {
            return false;
        } else if (error_code.equals("200")) {
            return true;
        }
        return false;
    }
}
