package com.cn.cof.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.cof.model.LoginInfo;
import com.cn.cof.entity.User;
import com.cn.cof.utils.HibernateUtil;
import com.cn.cof.utils.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Time:  2016/12/10 20:30
 * Email:  coffee377@dingtalk.com
 */
public class DataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //接收的参数
        String[] ops = {"login", "show", "register", "retake"};
        String[] urls = {"index.jsp", "home.jsp", "register.jsp", "retake.jsp"};
        //返回的状态
        String[] status = {"none", "success", "fail"};

        //响应数据
        JSONObject res = new JSONObject();
        JSONObject message = new JSONObject();

        request.setCharacterEncoding("utf-8");
        String op = request.getParameter("op");
        if (op == null) return;

        //对应操作的网页
        for (int i = 0; i < ops.length; i++) {
            if (op.equals(ops[i])) {
                res.put("url", urls[i]);
                break;
            }
        }

        //登录
        if (op.equals(ops[0])) {
            List users = null;
            LoginInfo info = null;
            String strJSON = JSONUtil.getJSONSting(request);
            if (!"".equals(strJSON)) {
                info = JSON.parseObject(strJSON, LoginInfo.class);
            }
            //查找用户名输入用户名的用户
            if (info != null) {
                users = HibernateUtil.executeQuery("from User u where u.username=?", info.getUsername());
            }

            if (users == null) {
                message.put("status", status[0]);//用户不存在
            } else {
                for (Object obj : users) {
                    User user = (User) obj;
                    if (info.getPassword().equals(user.getPassword())) {
                        message.put("status", status[1]);//成功匹配
                        message.put("url", urls[1]);
                    } else {
                        message.put("status", status[2]);//用户名密码不匹配
                    }
                }
            }
            res.put("message", message);
        }
        //显示数据
        else if (op.equals(ops[1])) {
            List users = HibernateUtil.executeQuery("from User");
            request.getSession().setAttribute("USERS",users);
            JSONArray jsonArray = new JSONArray();
            for (Object o : users) {
                User u = (User) o;
                u.setPassword(null);
                System.out.println(u);
                JSONObject obj = (JSONObject) JSON.toJSON(u);
                jsonArray.add(obj);
            }
            message.put("total", users.size());
            message.put("rows", jsonArray);
            message.put("footer", null);
            res.put("message", message);
        }


        String json = JSON.toJSONString(res);
        System.out.println(json);
        ServletOutputStream sos = response.getOutputStream();
        sos.write(json.getBytes("utf-8"));
        sos.flush();
        sos.close();
        res.clear();
    }

}
