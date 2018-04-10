package com.zqf.lifehelp.db.help;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * class from 封装RealmHelp
 * Created by zqf
 * Time 2018/4/10 13:46
 */

public class RealmHelp {

    private static RealmHelp instance = null;
    private static int RealmVersion = 1;
    private static String RealmDbFileName = "lifehelp.realm";

    public static RealmHelp getInstance() {
        if (instance == null) {
            instance = new RealmHelp();
        }
        return instance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        //初始化
        Realm.init(context);
        //使用默认Realm配置
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(RealmDbFileName).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        //自定义配置
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name(RealmDbFileName) //文件名
//                .schemaVersion(RealmVersion) //版本号
//                .deleteRealmIfMigrationNeeded()//版本冲突时自动删除原数据库
//                .build();
//        Realm.setDefaultConfiguration(config);
    }
}
