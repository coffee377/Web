$(function () {
    //传输的数据
    var data,
        layer = layui.layer;

    var getData = function () {
        data = {
            username: $("#login-username").val(),
            password: $("#login-password").val(),
            remember: $("#login-remember").val(),
            auto: $("#login-auto").val()
        };
    };

    var index1, index2;
    var $username = $("#login-username").blur(function () {
        if ($(this).val()) {
            getData();
            $.ajax({
                url: "login",   //请求的url地址
                type: "post",   //请求方式
                dataType: "json",    //返回格式为
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data), //参数值
                success: function (res) {   //请求成功时处理
                    if (res.status == "none") {
                        index1 = layer.tips("用户名不存在！", $("#login-username"), {
                            tips: [1, '#FF5722'],
                            time: 60000,
                            anim: 4
                        })
                    }
                }
            });
        } else {
            index2 = layer.tips("用户名不能为空！", $(this), {
                tips: [2, '#3595CC'],
                time: 5000
            })
        }
    }).focus(function () {
        $(this).select();
        layer.close(index1);
        layer.close(index2);
    });

    var $password = $("#login-password").blur(function () {
        if (!$(this).val()) {
            var index3 = layer.tips("密码不能为空！", $(this), {
                tips: [2, '#3595CC'],
                time: 5000
            })
        }
    }).focus(function () {
        $(this).select();
    });


    // TODO: 2016/12/10 0010 13:36 从Cookie中获取
    var $remember = $("#login-remember").val(false);
    var $auto = $("#login-auto").val(false);


    // 输入框获得、失去焦点事件
    $("input").focus(function () {
        $(this).parent().addClass("login-input-focus");
    }).blur(function () {
        $(this).parent().removeClass("login-input-focus");
    });

    //记住密码点击事件
    $remember.click(function () {
        if ($(this).attr("checked")) {
            $(this).removeAttr("checked");
            $(this).val(false);
        } else {
            $(this).attr("checked", true);
            $(this).val(true);
        }
    });

    //自动登录点击事件
    $auto.click(function () {
        if ($(this).attr("checked")) {
            $(this).removeAttr("checked");
            $(this).val(false);
        } else {
            $(this).attr("checked", true);
            $(this).val(true);
            if (!$remember.attr("checked")) {
                $remember.click();
            }
        }
    });

    //登录按钮
    $("#login-btn").click(function () {
        signIN();
    });

    //绑定回车
    $(document).keydown(function (e) {
        if (e.keyCode === 13) {
            signIN();
        }
    });

    /**
     * 登录
     */
    var signIN = function () {
        //用户名为空
        if (!$username.val() || !$password.val()) return;

        getData();
        //异步POST请求
        $.ajax({
            url: "login",   //请求的url地址
            type: "post",   //请求方式
            dataType: "json",    //返回格式为
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data), //参数值
            success: function (res) {   //请求成功时处理
                if (res.status == "none") {
                    layer.msg("用户不存在！", {icon: 5, offset: '100px', anim: 6});
                } else if (res.status == "fail") {
                    layer.msg("密码错误！", {icon: 2, offset: '100px'});
                } else if (res.status == "success") {
                    window.location.href = res.url;
                }
            },
            beforeSend: function () {
                layer.load(3, {
                    offset: '100px'
                });
            },
            complete: function () {
                layer.closeAll("tips");
                layer.closeAll("loading");
            },
            error: function () {

            }
        });


    };

});