<%@ page import="com.jyj.bean.User" %>
<%@page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%
    //获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>无标题文档</title>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <script src="/js/jquery-2.1.3.min.js"></script>
    <%--设置网页基链接地址--%>
    <base href="<%=basePath%>">
</head>
<body>
<div class="head">
    <div class="container">
        <div class="head_area">
            <div class="logo">
                LOGO
            </div>
            <div class="head_nav">
                <ul>
                    <li><a href="index.jsp">首页</a></li>
                    <li><a href="BookkeepingServlet" >记账</a></li>
                    <li><a href="ChartsServlet">报表</a></li>
                    <li><a href="personal.jsp">个人</a></li>
                </ul>
            </div>
            <div class="head_user">
                <%
                    User sessionUser = (User) session.getAttribute("SESSION_USER");
                    if (sessionUser == null) {
                %>
                <a href="login.jsp">登录</a>
                <a href="register.jsp">注册</a>
                <%
                } else {
                %>
                <span><%=sessionUser.getEmail()%></span>&nbsp;&nbsp;
                <a href="LogoutServlet">退出</a>
                <%
                    }
                %>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

</body>
</html>