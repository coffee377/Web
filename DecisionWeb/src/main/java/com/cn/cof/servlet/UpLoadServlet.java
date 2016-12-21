package com.cn.cof.servlet;

import com.alibaba.fastjson.JSONObject;
import com.cn.cof.utils.JSONUtil;
import com.cn.cof.utils.UpLoadFileName;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author:  Wu Yujie
 * Email:  coffee377@dingtalk.com
 * Time:  2016/12/19 16:43
 */
public class UpLoadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = getServletContext().getRealPath("/upload");

        //1 、创建文件解析对象
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        //2、进行文件解析后放在List中，因为这个类库支持多个文件上传，因此把结果会存在List中。
        List<FileItem> fileItems = null;

        try {
            fileItems = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String fileName = null;
        Iterator<FileItem> iter = fileItems.iterator();
        while (iter.hasNext()) {
            FileItem fileItem = iter.next();
            fileName = fileItem.getName();

            fileName = UpLoadFileName.reName(fileName);
            fileName = savePath + "/" + fileName;
            File file = new File(fileName);
            /*通过commons-io工具包生成文件，文件不存在则创建*/
            FileUtils.write(file, "UTF-8");
            try {
                fileItem.write(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("src", "upload/" + fileName);
        JSONUtil.sendData(response, jsonObject);
    }
}
