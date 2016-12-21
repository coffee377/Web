package com.cn.cof.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Time:  2016/12/10 23:31
 * Email:  coffee377@dingtalk.com
 */
/*声明当前类为hibernate映射到数据库中的实体类*/
@Entity
/*声明在数据库中自动生成的表名为t_user*/
@Table(name = "t_user")
//@NamedQueries(value = {
//        @NamedQuery(name = "getUserById", query = "from User where id = ?"),
//        @NamedQuery(name = "getUserByName", query = "from User where userName = ?"),
//        @NamedQuery(name = "getUserByRealName", query = "from User where realName = ?")
//})
public class User {
    @Id /*声明此列为主键*/
      
    /*
     *  @GeneratedValue注解来定义生成策略
     *  GenerationType.TABLES 当前主键的值单独保存到一个数据库的表中
     *  GenerationType.SEQUENCE  利用底层数据库提供的序列生成标识符
     *  GenerationType.IDENTITY 采取数据库的自增策略
     *  GenerationType.AUTO 根据不同数据库自动选择合适的id生成方案，这里使用mysql,为递增型
     */
    /*实例配置1.：*/
    @GeneratedValue(generator = "idG")
    @GenericGenerator(name = "idG", strategy = "native")
    private Integer id;

    @Column(unique = true, nullable = false, length = 60)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 60)
    private String realName;

    @Column(length = 20)
    private String mobile;

    @Column(length = 127)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    public User() {
    }

    public User(String userName, String password, String realName, String mobile, String email) {
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.mobile = mobile;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realname) {
        this.realName = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User && id.equals(((User) o).getId());
    }

    @Override
    public int hashCode() {
        return id;
    }
}
