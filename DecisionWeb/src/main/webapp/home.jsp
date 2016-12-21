<%--
  Created by IntelliJ IDEA.
  User: Wu Yujie
  Date: 2016/12/10 0010
  Time: 19:33
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="./css/demo.css">
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
    <style type="text/css" rel="stylesheet">
        #users {
            width: 750px;
            /*margin: 20px auto;*/
        }

        h2 {
            font-size: 24px;
        }

    </style>
</head>

<body>

<h2>${sessionScope.USER.userName}，您好，欢迎访问数据决策系统平台，上次访问时间
    <fmt:formatDate value="${sessionScope.USER.loginTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
</h2>

<table id="users" class="layui-table">
    <colgroup>
        <col width="50">
        <col width="100">
        <col width="100">
        <col width="120">
        <col width="100">
        <col width="150">
        <col width="120">
    </colgroup>
    <thead>
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>真实姓名</th>
        <th>密码</th>
        <th>手机</th>
        <th>邮箱</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${sessionScope.USERS}">
        <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>${user.realName}</td>
            <td>${user.password}</td>
            <td>${user.mobile}</td>
            <td>${user.email}</td>
            <td>
                <a class="update">
                    <button class="layui-btn layui-btn-mini">
                        <i class="layui-icon">&#xe642;</i>
                    </button>
                </a>
                <a class="delete">
                    <button class="layui-btn layui-btn-mini layui-btn-danger">
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>

    </tfoot>
</table>

<div id="page"></div>
<%--<table id="dg" class="easyui-datagrid"  title="决策系统用户信息" style="width:630px;height:400px"--%>
<%--data-options="rownumbers:true, singleSelect:true, checkOnSelect:true">--%>
<%--<thead>--%>
<%--<tr>--%>
<%--<th data-options="field:'ck',checkbox:true">选择</th>--%>
<%--<th data-options="field:'username',width:120,align:'left'">用户名</th>--%>
<%--<th data-options="field:'realname',width:100,align:'left'">姓名</th>--%>
<%--<th data-options="field:'mobile',width:150,align:'left'">手机</th>--%>
<%--<th data-options="field:'email',width:200,align:'left'">邮箱</th>--%>
<%--</tr>--%>
<%--</thead>--%>
<%--</table>--%>
<%--<a href="logout"><input type="button" value="退出"></a>--%>

<button id="exit" class="layui-btn layui-btn-big layui-btn-radius">退出</button>

</body>

<script type="text/javascript" src="./js/jquery-2.1.0.js"></script>
<%--<script type="text/javascript" src="./js/jquery.easyui.min.js"></script>--%>
<script type="text/javascript" src="layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    $.getJSON("data?op=show", {pageNumber: 1, pageSize: 5}, function (res) {
        $("#dg").datagrid('loadData', res.message);
    });

    layui.use(["laypage", "layer"], function () {
        var laypage = layui.laypage,
            layer = layui.layer;

        laypage({
            cont: $("#page"),
            pages: 10, //总页数
            groups: 5, //连续显示分页数
            jump: function (obj, first) {
                if (!first) {
                    layer.msg('第 ' + obj.curr + ' 页');
                }
            },
//            skip:true
        });

        $(".delete").click(function () {
            layer.confirm("确定要删除吗？", {icon: 3, title: '提示', offset: '200px'}, function (index) {
                layer.msg("确认");
                layer.close(index);
            });
        });

        $("#exit").click(function () {
            layer.confirm("确定要退出系统吗？", {icon: 3, title: '提示', offset: '200px'}, function (index) {
                window.location.href = "logout";
                layer.close(index);
            });
        })

    })
</script>
</html>
