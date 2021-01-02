<%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/26
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%
    //获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <base href="<%=basePath%>">
    <script src="/js/jquery-2.1.3.min.js"></script>
    <link rel="stylesheet" href="css/base.css">
</head>
<body>
<%--网站公共头--%>
<jsp:include page="top.jsp"></jsp:include>
<%-- <iframe src="top.jsp"  width="100%" height="100"  scrolling="no" frameborder="0"></iframe>--%>
<div class="swiper-area">
    <ul class="list">
        <li class="bg active"><img class="spic" src="img/banner_01.jpg" alt=""/></li>
        <li class="bg"><img class="spic" src="img/banner_03.jpg" alt=""/></li>
    </ul>
    <button type="button" class="btn" id="goPre"><</button>
    <button type="button" class="btn" id="goNext">></button>
    <script type="text/javascript">
        var bgs = document.getElementsByClassName('bg');
        var goPreBtn = document.getElementById('goPre');
        var goNextBtn = document.getElementById('goNext');
        var index = 0; //表示第几张在显示
        var clearActive = function () {
            for (var i = 0; i < bgs.length; i++) {
                bgs[i].className = "bg";
            }
        };
        var goIndex = function () {
            clearActive();
            bgs[index].className = "bg active";
        };
        var goNext = function () {
            if (index < 2) {
                index++;
            } else {
                index = 0; //回到第一张
            }

            goIndex();
        };
        var goPre = function () {
            if (index < 0) {
                index = 2;
            } else {
                index--;
            }

            goIndex();
        };
        goNextBtn.addEventListener('click', function () {
            goNext();
        });
        goPreBtn.addEventListener('click', function () {
            goPre();
        });
        //定时器2000ms跳转
        var time = 0;
        setInterval(function () {
            time++; //每100ms,time++
            if (time == 20) {
                goNext(); //2000ms跳转
                time = 0; //执行完goNext()重置0；
            }

        }, 100);
    </script>
</div>
</body>
</html>
