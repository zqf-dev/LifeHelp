package com.zqf.lifehelp.utils;

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * class from 工具类
 * Created by zqf
 * Time 2017/6/15 16:01
 */

public class Util {

    public static void NextActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Number) {
            Number num = (Number) obj;
            if (num.intValue() == 0) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof String) {
            String str = (String) obj;
            if ((str == null) || str.equals("")) {
                return true;
            } else {
                return false;
            }
        } else if (obj instanceof Collection<?>) {
            Collection<?> c = (Collection<?>) obj;
            return c.isEmpty();
        } else if (obj instanceof Map<?, ?>) {
            Map<?, ?> m = (Map<?, ?>) obj;
            return m.isEmpty();
        } else if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            return length == 0 ? true : false;
        } else {
            return false;
        }
    }
}
