package com.cn.cof.biz;

import com.cn.cof.entity.page.PageBean;
import com.cn.cof.entity.User;

import javax.servlet.http.HttpSession;

/**
 * 用户模块（User）业务接口
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/13 11:19
 */
public interface UserBiz {
    /**
     * 验证用户
     *
     * @param userName 用户名
     * @param password 密码
     * @return int
     */
    int verify(String userName, String password);

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功返回当前登录用户
     */
    User login(String userName, String password);

    /**
     * 注销用户
     *
     * @param session   HttpSession
     */
    void logout(HttpSession session);


    /**
     * 分页查询用户数据
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param hql        占位符 HQL 语句
     * @param params     可变参数
     * @return 指定页码数据集合
     */
    PageBean<User> queryForPage(int pageNumber, int pageSize, String hql, Object... params);


}
