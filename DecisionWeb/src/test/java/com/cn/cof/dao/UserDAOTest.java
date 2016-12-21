package com.cn.cof.dao;

import com.cn.cof.dao.impl.UserDAOImpl;
import com.cn.cof.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/15 12:38
 */
public class UserDAOTest {
    private UserDAO userDAO = new UserDAOImpl();
    private List<User> list;

    @Test
    public void queryForPage() throws Exception {
        list = userDAO.queryForPage(1,5,"from User");
        System.out.println(list.size());
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void getTotalCount() throws Exception {
        System.out.println(userDAO.getTotalCount("from User"));
    }

    @Test
    public void getUserByName() throws Exception {
        list = userDAO.getUserByName("demo");
        assertEquals(false, list.size() == 0);
    }


}