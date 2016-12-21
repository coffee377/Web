<%--
  Created with IntelliJ IDEA.
  Author:  Wu Yujie
  Email:  coffee377@dingtalk.com
  Time:  2016/12/11 15:42
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <link rel="stylesheet" type="text/css" href="./css/demo.css">
    <title>用户注册</title>
</head>
<script type="text/javascript" src="./js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
<script>
    $(function () {
        $('.easyui-panel').panel({
            width:400,
            height:400,
            title:'用户注册',
        });

        $(".easyui-textbox").tooltip({
            position: 'top',
            content: '<span style="color:#fff">This is the tooltip message.</span>',
            onShow: function(){
                $(this).tooltip('tip').css({
                    backgroundColor: '#666',
                    borderColor: '#666'
                });
            }
        });
    })
</script>
<body>
<%
    //out.print("欢迎注册用户");
%>
<div class="easyui-panel">
    <div style="padding:10px 60px 20px 60px">
        <form id="ff" method="post">
            <table cellpadding="5">
                <tr>
                    <td>用名:</td>
                    <td><input class="easyui-textbox" type="text" name="username" data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>密码:</td>
                    <td><input class="easyui-textbox" type="password" name="password" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><input class="easyui-textbox" type="password" name="confirm" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>真实姓名:</td>
                    <td><input class="easyui-textbox" type="text" name="realname" data-options="required:true"></td>
                </tr>
                <tr>
                    <td>手机:</td>
                    <td><input class="easyui-textbox" type="text" name="mobile"
                               data-options="required:true,validType:'phone'"></td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td><input class="easyui-textbox" type="text" name="email"
                               data-options="required:true,validType:'email'"></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
        </div>
    </div>
</div>
<script>
    function submitForm() {
        $('#ff').form('submit');
    }
    function clearForm() {
        $('#ff').form('clear');
    }
</script>
</body>
</html>
