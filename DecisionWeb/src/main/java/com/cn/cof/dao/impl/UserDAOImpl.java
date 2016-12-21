package com.cn.cof.dao.impl;

import com.cn.cof.dao.UserDAO;
import com.cn.cof.entity.User;
import com.cn.cof.utils.HibernateUtil;
import com.cn.cof.utils.PageUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/13 13:54
 */
public class UserDAOImpl implements UserDAO {

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return  用户对象集合
     */
    @Override
    public List<User> getUserByName(String userName) {
        String hql = "from User u where u.userName=?";
        List<User> list1 = HibernateUtil.executeQuery(hql, userName);
        if (list1 != null) {
            return list1;
        }
        return null;
    }

    /**
     * 分页查询
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param hql        占位符 HQL 语句
     * @param params     可变参数
     * @return  查询指定页码的用户集合
     */
    @Override
    public List<User> queryForPage(int pageNumber, int pageSize, String hql, Object... params) {
        int offset = PageUtil.countOffset(pageNumber,pageSize);
        return HibernateUtil.executeQuery(offset,pageSize,hql,params);
    }

    /**
     *  查询出记录的数量
     * @param hql    占位符 HQL 语句
     * @param params 可变参数
     * @return  总记录数
     */
    @Override
    public Integer getTotalCount(String hql, Object... params) {
        return HibernateUtil.getCount(hql,params);
    }


}
