package com.cn.cof.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/13 18:17
 */
public class JSONUtil {
    public static String getJSONSting(HttpServletRequest request) throws IOException {
        InputStream in = request.getInputStream();
        Reader reader = new InputStreamReader(in, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        bufferedReader.close();
        reader.close();
        in.close();
        return sb.toString();
    }

    public static JSONObject getData(HttpServletRequest request) throws IOException {
        return  JSON.parseObject(getJSONSting(request));
    }

    public static void sendData(HttpServletResponse response, JSONObject jsonObject) throws IOException {
        String json = JSON.toJSONString(jsonObject);

        // TODO: 2016/12/21 9:56 删除
        System.out.println(json);

        ServletOutputStream sos;
        sos = response.getOutputStream();
        sos.write(json.getBytes("utf-8"));
        sos.flush();
        sos.close();
    }

}