<%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/27
  Time: 15:52
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
    <title>登录</title>
</head>
<body>
<%
    String email = "";
    String password = "";
    //从客户端读取Cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("COOKIE_EMAIL".equals(cookie.getName())) {
                //解密获取存储在Cookie中的用户的email
                email = com.jyj.utils.CookieEncryptTool.decodeBase64(cookie.getValue());
            }
            if ("COOKIE_PASSWORD".equals(cookie.getName())) {
                //解密获取存储在Cookie中的用户的密码
                password = com.jyj.utils.CookieEncryptTool.decodeBase64(cookie.getValue());
            }
        }
    }
%>

<jsp:include page="top.jsp"></jsp:include>
<div class="container">
    <div class="superbox">
        <div class="loginbox">
            <h2 class="logo">登录</h2>
            <form action="LoginServlet" method="post">
                <div class="zhanghao">
                    邮箱：<input type="text" id="email" name="email" value="<%=email%>"/></br>
                    密码：<input type="password" id="password" name="password" value="<%=password%>"/>
                </div>
                <div class="login">
                    <input type="submit" name="submit" value="登录"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" value="清空" id="reset">
                    <span><input type="checkbox" id="remeberMe" name="remeberMe" value="true" checked="checked">记住密码</span>
                </div>
                <br/>
                <span class="word">还没有账号？<a href="register.jsp" style="color:#627bc9">去注册</a></span>
            </form>
            <script>
                $(document).ready(function () {
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
