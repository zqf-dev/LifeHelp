package com.zqf.lifehelp.utils.listener;

import java.util.List;

/**
 * @author zqf
 *         权限申请回调的接口
 */
public interface PermissionListener {

    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
