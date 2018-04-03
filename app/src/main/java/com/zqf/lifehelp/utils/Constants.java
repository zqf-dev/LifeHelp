package com.zqf.lifehelp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/1/5 11:32
 */

public class Constants {

    public static String MOBKEY = "1b8a769191c30";

    public static String BASE_URL = "http://apicloud.mob.com";

    public static String CityAssets = "city.txt";

    public static int page_size = 10;//分页数据条数


    //标签数据
    public static List<String> dataSource;

    static {
        dataSource = new ArrayList<>();
        dataSource.add("天气预报");
        dataSource.add("身份证查询");
        dataSource.add("彩票开奖");
        dataSource.add("菜谱大全");
        dataSource.add("周公解梦");
        dataSource.add("八字算命");
        dataSource.add("健康知识");
        dataSource.add("今日油价");
        dataSource.add("基站查询");
    }
}
