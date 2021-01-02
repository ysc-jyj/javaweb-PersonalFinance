<%@ page import="com.jyj.bean.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/29
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>个人信息</title>
    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/personal.css"/>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-3.5.1.min.js"></script>
    <script src="/js/jquery-2.1.3.min.js"></script>
    <%--<base href="<%=basePath%>">--%>
    <script type="text/javascript">
        function validate() {
            var headShot = document.getElementById("headShot");
            if (headShot.value == "") {
                alert("请选择要上传的头像！");
                headShot.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%
    User sessionUser = (User) session.getAttribute("SESSION_USER");
%>
<jsp:include page="top.jsp"></jsp:include>
<div class="container">
    <div class="superbox">
        <form action="${pageContext.request.contextPath}/PersonalInfoServlet?method=upload" method="post"
              enctype="multipart/form-data" onsubmit="return validate();">

            <div class="right">
                <h2>上传头像</h2>
                <%if (sessionUser.getHeadshot() != null) {%>
                <img name="headshot" width="100px" height="100px"
                     src="${pageContext.request.contextPath}/img/<%=sessionUser.getHeadshot()%>">
                <%} else {%>
                <img width="100px" height="100px" src="${pageContext.request.contextPath}/img/anonymous.png">
                <%}%>
                <input name="headShot" id="headShot" type="file" value="上传头像">
                <input name="submit" type="submit" class="save1" value="保存">
                <input name="reset" type="reset" class="cancel2" value="取消">
            </div>
        </form>
        <form  method="post">
            <h2>修改信息</h2>
            <div class="left">

                <table>
                    <tr>
                        <td>邮箱：</td>
                        <td>
                            <input type="text" disabled="disabled" name="email" value="<%=sessionUser.getEmail()%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td>用户名：</td>
                        <td><input type="text" disabled="disabled" name="username"
                                   value="<%=sessionUser.getUsername()%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><input type="password" disabled="disabled" name="password"
                                   value="<%=sessionUser.getPassword()%>"></td>
                    </tr>
                    <tr class="hidden">
                        <td>原密码：</td>
                        <td><input type="text" id="old"></td>
                    </tr>
                    <tr class="hidden">
                        <td>新密码</td>
                        <td><input type="text" id="new"></td>
                    </tr>
                </table>
                <button id="modify">修改</button>
                <button id="save" disabled="disabled" class="hidden">保存</button>
                <button id="cancel" disabled="disabled" type="reset" class="hidden">取消</button>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    $("#modify").click(function () {//修改点击事件
        $("input[name=username]").attr("disabled", false);
        $("input[name=email]").attr("disabled", true);
        //$("input[name=password]").attr("disabled", false);
        this.setAttribute("disabled", true);
        $("#save").attr("disabled", false);
        $("#save").removeAttr("class");
        $("#cancel").attr("disabled", false);
        $("#cancel").removeAttr("class");
        $(".hidden").removeAttr("class");
        $(".hidden").removeAttr("class");

    });
    $("#save").click(function () {//保存修改事件
        var username = $("input[name=username]").val();
        var password = $("input[name=password]").val();
        var oldpassword = $("input[id=old]").val();
        var newpassword = $("input[id=new]").val();
        var email = $("input[name=email]").val();
        if (!username || !oldpassword || !newpassword) {
            alert("不能为空");
            return false;
        }
        if (password != oldpassword) {
            alert("原密码输入错误，请重新输入");
            return false;
        }
        $.ajax({
            type: 'post',
            url: 'PersonalInfoServlet',
            dataType: 'text',

            cache: false,
            async: false,//请求改为同步方式

            data: {
                email: email,
                username: username,
                password: oldpassword,
                newpassword: newpassword
            },

            success: function (data) {
                if (data == "ok") {
                    alert('修改成功！');
                    //console.log('修改成功!');
                    $("input").attr("disabled", true);
                    /*window.location.href="PersonalInfoServlet";*/
                } else {
                    alert("修改失败！");
                }
            },
            /*error: function (data) {
                alert("失败！！！");
            }*/
        });
    });
</script>
</html>
