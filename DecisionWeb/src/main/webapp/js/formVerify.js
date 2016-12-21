/**
 * Created by Wu Yujie on 2016/12/20.
 */

$.extend($.validator.messages, {
    required: "必填字段",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: $.validator.format("请输入一个长度最多是{0}的字符串"),
    minlength: $.validator.format("请输入一个长度最少是{0}的字符串"),
    rangelength: $.validator.format("请输入一个长度介于{0}和{1}之间的字符串"),
    range: $.validator.format("请输入一个介于{0}和{1}之间的值"),
    max: $.validator.format("请输入一个最大为{0}的值"),
    min: $.validator.format("请输入一个最小为{0}的值")
});
var status;
/**
 * 向服务器发送异步请求，判断用户是否存在
 * @param value 用户名
 * @returns {number}
 */
function verify(value) {
    $.ajax({
        url: "login",   //请求的url地址
        type: "post",   //请求方式
        dataType: "json",    //返回格式为
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({username: value}), //参数值
        success: function (res) {   //请求成功时处理
            status = res.status
        }
    });
}
// verify($("input[name='userName']").val());
// $(document).ready(function () {
$("#userName").blur(function () {
    // alert($(this).val());
    // verify($(this).val());
});
// });
function exist() {
    return false
}

/*验证demo表单start*/
$(function () {
    $.validator.addMethod("userNameVerify", function (value, element) {
        return this.optional(element) || !exist();
    }, "用户名已存在，请重新输入！");
    $.validator.addMethod("mobileVerify", function (value, element) {
        var mobileMatch = /^1[34578]\d{9}$/;
        return this.optional(element) || (mobileMatch.test(value));
    }, "手机号码有误，请重新输入！");
    $.validator.addMethod("realNameVerify", function (value, element) {
        var mobileMatch = /^[\u4E00-\u9FA5]+$/;
        return this.optional(element) || (mobileMatch.test(value));
    }, "格式不正确，请重新输入！");


    $('#edit').validate({
        errorElement: "span",
        errorClass: "error",
        validClass: "valid",

        // onfocusin: function (element) {
//                element.parent().next().append();
//                $(element).removeClass("false");
//                $(element).parent().next().removeClass("false");
//                $("span").prev().removeClass("fa fa-lg fa-times");
//         },
        onfocusout: function (element) {
            // verify($(element).val());
            $(element).valid();
        },
        errorPlacement: function (error, element) {
            $(element).parent().append(error);
            $(element).parent().find("span").addClass("errTips");
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass(errorClass).removeClass(validClass);
            $(element).parent().next().removeClass(validClass).addClass(errorClass);
            $(element).parent().next().find("i").removeClass("fa fa-lg fa-check").addClass("fa fa-lg fa-times");
            $(element).parent().find("span").addClass("errTips");
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).addClass(validClass).removeClass(errorClass);
            $(element).parent().next().removeClass(errorClass).addClass(validClass);
            $(element).parent().next().find("i").removeClass("fa fa-lg fa-times").addClass("fa fa-lg fa-check");
            $(element).parent().find("span").removeClass("errTips");
        },
        success: function (span) {
            span.parent().next().removeClass("error").addClass("valid");
        },
        rules: {
            userName: {
                required: true
            },
            password: {
                required: true,
                minlength: 6
            },
            password2: {
                required: true,
                equalTo: "#password",
                minlength: 6
            },
            realName: {
                required: true
            },
            mobile: {
                required: true
            },
            email: {
                required: true,
                email: "#email"
            }
        },
        messages: {
            userName: {
                required: '请设置一个用户名'
            },
            password: {
                required: '请设置一个密码',
                minlength: '密码长度不小于6个字符',
            },
            password2: {
                required: '请再次确认密码',
                equalTo: '两次输入密码不相同',
                minlength: '密码长度不小于6个字符'
            },
            realName: {
                required: '请输入您的真实姓名'
            },
            mobile: {
                required: '请输入您常用的手机号码'
            },
            email: {
                required: '请输入您常用的电子邮箱',
                email: "请输入正确格式的电子邮件"
            }
        }
    });
});
/*验证demo表单end*/

layui.use(["upload", "layer", "form"], function () {
    var upload = layui.upload,
        form = layui.form();
    upload({
        url: "upload",//上传接口
        success: function (res) { //上传成功后的回调
            $("#upload_image").attr("src", res.src)
        }
    });


    form.on('submit(formDemo)', function (data) {
        layer.msg(JSON.stringify(data.field));
        return false;
    });

});