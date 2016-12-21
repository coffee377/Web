package com.cn.cof.dao.base;

import java.util.List;

/**
 * 数据库操作基类接口
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/15 16:00
 *
 * @param <T> 实体对象
 */
public interface DAO<T> {
    /**
     * 查询一条记录
     *
     * @param hql    占位符 HQL 语句
     * @param params 可变参数
     * @return 返回一个对象
     */
    T get(String hql, Object... params);

    /**
     * 查询多条记录
     *
     * @param hql    占位符 HQL 语句
     * @param params 可变参数
     * @return 返回对象集合
     */
    List<T> getAsList(String hql, Object... params);

    /**
     * 查询某条记录的某一个字段的值或一个统计的值
     *
     * @param hql    占位符 HQL 语句
     * @param params 可变参数
     * @param <E>    返回类型
     * @return E
     */
    <E> E getValue(String hql, Object... params);

    /**
     * 分页查询
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @param hql        占位符 HQL 语句
     * @param params     可变参数
     * @return 返回List对象集合
     */
    List<T> queryForPage(int pageNumber, int pageSize, String hql, Object... params);


    /**
     * 添加
     *
     * @param v 实体对象
     * @return 添加成功返回 TRUE ，否则返回 FALSE
     */
    boolean save(T v);

    /**
     * 更新
     *
     * @param v 实体对象
     * @return 更新成功返回 TRUE ，否则返回 FALSE
     */
    boolean update(T v);

    /**
     * 删除
     *
     * @param v 实体对象
     * @return 删除成功返回 TRUE ，否则返回 FALSE
     */
    boolean delete(T v);

}
