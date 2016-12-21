package com.cn.cof.model;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Time:  2016/12/10 18:44
 * Email:  coffee377@dingtalk.com
 */
public class LoginInfo {
    private String username;
    private String password;
    private String remember;
    private String auto;

    public LoginInfo() {
    }

    public LoginInfo(String username, String password, String remember, String auto) {
        this.username = username;
        this.password = password;
        this.remember = remember;
        this.auto = auto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remember='" + remember + '\'' +
                ", auto='" + auto + '\'' +
                '}';
    }
}
