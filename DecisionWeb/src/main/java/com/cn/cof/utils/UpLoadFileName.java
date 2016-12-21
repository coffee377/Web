package com.cn.cof.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/19 17:05
 */
public class UpLoadFileName {
    public static String reName(String fileName){
        // 获取到文件的后缀名
        String fix = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 通过util中的Date获取当前时间
        Date nowDate = new Date();
        // 格式化时间对象返回字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        fileName = sdf.format(nowDate);
        // 毫秒数,类似于随机数为了避免文件重名
        fileName += System.currentTimeMillis();

        fileName += "." + fix;
        return fileName;
    }
}
