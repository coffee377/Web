package com.cn.cof.biz.impl;

import com.cn.cof.biz.UserBiz;
import com.cn.cof.dao.UserDAO;
import com.cn.cof.dao.impl.UserDAOImpl;
import com.cn.cof.entity.page.PageBean;
import com.cn.cof.entity.User;
import com.cn.cof.utils.PageUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 业务逻辑接口
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/13 13:10
 */
public class UserBizImpl implements UserBiz {
    private UserDAO userDAO = new UserDAOImpl();

    /**
     * 校验用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return -1：用户不存在    0：用户密码错误   1：匹配车成功
     */
    @Override
    public int verify(String userName, String password) {
        List<User> users = userDAO.getUserByName(userName);
        if (users != null) {
            for (User u : users) {
                // TODO: 2016/12/13 15:07 数据库中的密码应该是加密的，实现加密算法
                if (u.getPassword().equals(password)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return -1;
    }

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功返回当前登录用户
     */
    @Override
    public User login(String userName, String password) {
        if (verify(userName, password) == 1) {
            return userDAO.getUserByName(userName).get(0);
        }
        return null;
    }


    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /**
     * 分页查询用户数据
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param hql        占位符 HQL 语句
     * @param params     可变参数
     * @return 指定页码数据集合
     */
    @Override
    public PageBean<User> queryForPage(int pageNumber, int pageSize, String hql, Object... params) {
        List<User> userList = userDAO.queryForPage(pageNumber, pageSize, hql, params);
        int totalSize = userDAO.getTotalCount(hql, params);
        int totalPage = PageUtil.countTotalPage(totalSize, pageSize);
        return new PageBean<>(userList, totalSize, totalPage, pageNumber, pageSize);
    }
}
