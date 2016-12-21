package com.cn.cof.utils;

import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/15 18:26
 */
public class HibernateUtilTest {
    @Test
    public void schemaExport() throws Exception {
        HibernateUtil.schemaExport();
    }


    @Test
    public void getCount() throws Exception {
        System.out.println(HibernateUtil.getCount("from User"));
    }

    @Test
    public void executeQuery() throws Exception {
        List list = HibernateUtil.executeQuery(5,5,"from User");
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o);
        }
    }

}