<%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/27
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/LoginandRegister.css">
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>
    <script src="/js/jquery-2.1.3.min.js"></script>
    <title>注册</title>
    <script>
        function validate() {
            var email = document.getElementById("email");
            var password = document.getElementById("password");
            var agree = document.getElementById("agree");
            var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

            if (email.value == "") {
                alert("邮箱不能为空！");
                email.focus();
                return false;
            } else if (!pattern.test(email.value)) {
                alert("请输入正确的邮箱格式！");
                email.focus();
                return false;
            } else if (email.length > 16) {
                alert("邮箱长度不符合要求!！");
                email.focus();
                return false;
            }
            if (password.value == "") {
                alert("密码不能为空！");
                password.focus();
                return false;
            } else if (password.length < 6 || password.length > 12) {
                alert("密码长度不符合要求，请输入6-12位密码!");
                password.focus();
                return false;
            }
            return ture;
        }
    </script>
    <script>

        $(function () {
            //文本框点击事件
            $("input[name='email']").blur(function () {
                //发送ajax请求
                $.ajax({
                    type: "POST",
                    url: "QueryServlet",
                    data: {
                        email: $("#email").val()
                    },
                    dataType: "json",
                    cache: false,
                    async: false,
                    error: function () {
                        alert('ajax请求请求错误...')
                    },
                    success: function (data) {
                        //清空
                        $("#showRet").empty();
                        if (data.userExsit) {
                            //将接收到的数据显示到文本框
                            $("#showRet").text("邮箱已经被注册！");
                            $("#showRet").css("color", "red");
                            $("#showRet").css("font-size", "normal");
                        } else {
                            $("#showRet").text("邮箱未被注册");
                            $("#showRet").css("color", "green");
                            $("#showRet").css("font-size", "normal");
                        }
                    },
                })
            })
        });


    </script>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div class="container">
    <div class="superbox">
        <div class="loginbox">
            <h2 class="logo">注册</h2>
            <form action="RegisterServlet" method="post" onsubmit="return validate();">
                <div class="zhanghao" id="zhanghao">
                    邮箱：<input type="text" id="email" name="email" onblur="validateEmail()"/>
                    <span id="showRet"></span></br>
                    <%--<lable style="color:red" id="emailValidate"onblur="ajaxValidate(this)></lable>--%>
                    密码：<input type="password" id="password" name="password"/>
                </div>

                <div class="login">
                    <input type="submit" name="submit" value="注册"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="重置" id="reset">
                </div>
                <br/>
                <span class="word">已有账号？<a href="login.jsp">去登录</a></span>
            </form>
            <script>
                $(document).ready(function () {//清空函数
                    $("#reset").click(function () {
                        $("#email").val("");
                        $("#password").val("");
                    });
                });
            </script>

        </div>
    </div>
</div>
</body>
</html>
