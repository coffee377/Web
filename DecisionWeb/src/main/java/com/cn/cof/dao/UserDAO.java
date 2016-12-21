package com.cn.cof.dao;

import com.cn.cof.entity.User;

import java.util.List;

/**
 * 数据库访问操作接口
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/13 13:47
 */
public interface UserDAO {
    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 用户集合
     */
    List<User> getUserByName(String userName);

    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param hql        占位符 HQL 语句
     * @param params     可变参数
     * @return 返回List对象集合
     */
    List<User> queryForPage(int pageNumber, int pageSize, String hql, Object... params);

    /**
     * 查询出记录的数量
     *
     * @param hql    占位符 HQL 语句
     * @param params 可变参数
     * @return 查询总记录数
     */
    Integer getTotalCount(String hql, Object... params);
}
