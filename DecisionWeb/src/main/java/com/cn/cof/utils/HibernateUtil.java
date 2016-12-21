package com.cn.cof.utils;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Hibernate Version 5.2.4.Final
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Time:  2016/12/10 23:43
 * Email:  coffee377@dingtalk.com
 */
public class HibernateUtil {
    private static final SessionFactory factory;
    private static String HIBERNATE_CONFIG_FILE = "config/hibernate.cfg.xml";
    private static Metadata metadata;

    static {
        /**
         * 1. 配置类型安全的准服务注册类，这是当前应用的单例对象，不作修改，所以声明为final
         * 在configure("config/hibernate.config.xml")方法中，如果不指定资源路径，默认在类路径下
         * 寻找名为hibernate.config.xml的文件
         */
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(HIBERNATE_CONFIG_FILE)
                .build();
        metadata = new MetadataSources(registry).buildMetadata();

        /**
         * 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
         */
        factory = metadata.buildSessionFactory();
    }

    /**
     * 生成数据库表结构
     */
    public static void schemaExport() {
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

    public static Session getSession() {
        return factory.getCurrentSession();
    }

    public static void save(Object obj) {
        save(getSession(), obj);
    }

    public static void save(Session session, Object obj) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 执行 Hibernate 的 HQL 查询语句
     *
     * @param hql    占位符HQL语句
     * @param params 可变参数
     * @return 结果集
     */
    public static ArrayList executeQuery(String hql, Object... params) {
        Session session = getSession();
        Transaction tx = null;
        ArrayList list;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
            }

            list = (ArrayList) query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return list;
    }

    /**
     * 返回记录条数
     *
     * @param hql 占位符HQL语句
     * @return Integer
     */
    public static Integer getCount(String hql,Object... params) {
        return HibernateUtil.executeQuery(hql,params).size();
    }

    /**
     * 分页查询
     *
     * @param offset 查询偏移量
     * @param length 每页记录数
     * @param hql    占位符 HQL 语句
     * @param params 可变参数
     * @return 结果集
     */
    public static ArrayList<com.cn.cof.entity.User> executeQuery(int offset, int length, String hql, Object... params) {
        Session session = getSession();
        Transaction tx = null;
        ArrayList<com.cn.cof.entity.User> list;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
            }
            query.setFirstResult(offset);
            query.setMaxResults(length);
            list = (ArrayList<com.cn.cof.entity.User>) query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return list;
    }

    /**
     * 执行 Hibernate 的 HQL 语句,包括UPDATE, DELETE
     *
     * @param hql    占位符HQL语句
     * @param params 可变参数
     */
    public static void executeUpdate(String hql, Object... params) {
        Session session = getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    query.setParameter(i, params[i]);
                }
            }
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
    }

}
