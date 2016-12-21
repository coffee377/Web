package com.cn.cof.biz;

import com.cn.cof.biz.impl.UserBizImpl;
import com.cn.cof.dao.UserDAO;
import com.cn.cof.dao.impl.UserDAOImpl;
import com.cn.cof.entity.User;
import com.cn.cof.entity.page.PageBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/15 13:08
 */
public class UserBizTest {

    private UserBiz userBiz = null;

    @Before
    public void setUp() throws Exception {
        userBiz = new UserBizImpl();
    }

    @After
    public void tearDown() throws Exception {
        if (userBiz != null) {
            userBiz = null;
        }
    }

    @Test
    public void verify() throws Exception {
        assertEquals(-1, userBiz.verify("dem", "123456"));
        assertEquals(0, userBiz.verify("demo", ""));
        assertEquals(1, userBiz.verify("demo", "123456"));
    }

    @Test
    public void login() throws Exception {
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUserByName("coffee377").get(0);
        assertEquals(user, userBiz.login("coffee377", "wuyujie"));
        assertEquals(null, userBiz.login("demo", "1234567"));
        assertEquals(null, userBiz.login("demo2", "123456"));
    }

    @Test
    public void queryForPage() throws Exception {
        PageBean<User> pageBean = userBiz.queryForPage(1, 5, "from User");
        System.out.println(pageBean);
    }

}