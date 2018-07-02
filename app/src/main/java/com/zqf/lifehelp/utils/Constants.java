package com.zqf.lifehelp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/1/5 11:32
 */

public class Constants {

    //MOB_KEY
    public static String MOBKEY = "1b8a769191c30";
    //BASE_URL
    public static String BASE_URL = "http://apicloud.mob.com";
    //城市选择文件
    public static String CityAssets = "city.txt";
    //分页数据条数
    public static int page_size = 10;
    //标签数据
    public static List<String> dataSource;
    //标签是否选择
    public static String isSelectTag = "isSelectTag";

    static {
        dataSource = new ArrayList<>();
        dataSource.add("天气预报");
        dataSource.add("彩票开奖");
        dataSource.add("身份证查询");
        dataSource.add("菜谱大全");
        dataSource.add("周公解梦");
        dataSource.add("八字算命");
        dataSource.add("健康知识");
        dataSource.add("今日油价");
        dataSource.add("基站查询");
    }
}
