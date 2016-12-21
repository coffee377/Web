package com.cn.cof.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.cof.biz.UserBiz;
import com.cn.cof.biz.impl.UserBizImpl;
import com.cn.cof.entity.User;
import com.cn.cof.model.LoginInfo;
import com.cn.cof.utils.HibernateUtil;
import com.cn.cof.utils.JSONUtil;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/13 15:21
 */
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;chartset=utf-8");
        String str = getServletContext().getRealPath("/WEB-INF/classes/config/");
        System.out.println(str);

        String name1 = getServletConfig().getInitParameter("name");
        System.out.println(name1);
        request.setCharacterEncoding("utf-8");
        String strJSON = JSONUtil.getJSONSting(request);
        LoginInfo info = JSON.parseObject(strJSON, LoginInfo.class);
        String name = info.getUsername();
        String pwd = info.getPassword();
        JSONObject res = new JSONObject();
        UserBiz userBiz = new UserBizImpl();

        User user = userBiz.login(name, pwd);
        if (user != null) {
            res.put("status", "success");
            res.put("url", "home.jsp");
            //登录成功将当前用户信息存入Session，更新用户登录时间
            request.getSession().setAttribute("USER", userBiz.login(name, pwd));

            user.setLoginTime(new Date());
            Session session = HibernateUtil.getSession();
            session.beginTransaction().begin();
            session.update(user);
            session.getTransaction().commit();

            List users = HibernateUtil.executeQuery("from User");
            request.getSession().setAttribute("USERS",users);

            Cookie cookie1 = new Cookie("REMEMBER", info.getRemember());
            Cookie cookie2 = new Cookie("AUTO", info.getAuto());
            cookie1.setMaxAge(7 * 24 * 3600);
            cookie2.setMaxAge(7 * 24 * 3600);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            JSONUtil.sendData(response, res);
        } else {
            if (userBiz.verify(name, pwd) == 0) {
                res.put("status", "fail");
            } else {
                res.put("status", "none");
            }
            JSONUtil.sendData(response, res);
        }

    }
}
