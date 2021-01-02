<%@ page import="com.jyj.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/28
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>无标题文档</title>
    <base href="<%=basePath%>">
    <script src="/js/jquery-2.1.3.min.js"></script>
    <%
        User user = (User) session.getAttribute("SESSION_USER");
    %>
</head>
<body>
<div class="it_aside">
    <div class="it_aside_fixed">

        <div class="headshot">
            <%if (user.getHeadshot()!=null){%>
            <img width="100px" height="100px" src="${pageContext.request.contextPath}/img/<%=user.getHeadshot()%>">
            <%}else{%>
            <img  width="100px" height="100px" src="${pageContext.request.contextPath}/img/anonymous.png">
            <%}%>
        </div>

        <div class="info">
            <%
                if (user == null) {
            %>
            <p class="usrname">username</p>
            <p class="email">email</p>
            <%
            } else {
            %>
            <p class="usrname">用户名:<%=user.getUsername()%>
            </p>
            <p class="email">邮箱:<%=user.getEmail()%>
            </p>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
