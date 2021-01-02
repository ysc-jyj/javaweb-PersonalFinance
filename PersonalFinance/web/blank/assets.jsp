<%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/31
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="myAsset">

    <div class="asset">
        <c:if test="${not empty requestScope.assets}">

            <div>
                <p>银行卡</p>
                <p><c:out value="${requestScope.assets.card}"></c:out> </p>
            </div>
            <div>
                <p>微信</p>
                <p><c:out value="${requestScope.assets.weChat}"></c:out></p>
            </div>
            <div>
                <p>支付宝</p>
                <p><c:out value="${requestScope.assets.alipay}"></c:out></p>
            </div>
            <div>
                <p>现金</p>
                <p><c:out value="${requestScope.assets.other}"></c:out></p>
            </div>
        </c:if>
    </div>

</div>
</body>
</html>
