<%--
  Created by IntelliJ IDEA.
  User: ysc
  Date: 2020/12/29
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.jyj.utils.DateUtils" %>
<%@ page import="com.jyj.bean.Payment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
弃用
<html>
<head>
    <title>财务明细表</title>
<%--    <%
        Payment payment = (Payment) request.getAttribute("payment");
    %>--%>
</head>
<body>
<%--    <jsp:useBean id="pagination" class="com.jyj.bean.TablePage" scope="request"></jsp:useBean>--%>
    <table border="1" cellspacing="" cellpadding="">
        <caption>财务明细</caption>
        <thead>
        <tr class="tr1">
            <th>日期</th>
            <th>收/支</th>
            <th>类型</th>
            <th>支付方式</th>
            <th>金额</th>
            <th>备注</th>
        </tr>
        </thead>
        <c:if test="${not empty requestScope.TablePage.list}">
            <c:forEach items="${requestScope.TablePage.list}" var="payment">

                <tbody>
                <tr>
                    <%--bug--%>
                    <td>${payment.pdate} </td>
                    <td>${payment.payments}</td>
                    <td>${payment.type}</td>
                    <td>${payment.way}</td>
                    <td>${payment.money}</td>
                    <td>${payment.remarks}</td>
                </tr>
                </tbody>
            </c:forEach>
        </c:if>
    </table>
    <div align="center">
        <a href="javascript:beforePage('${TablePage.beforePage}')">上一页</a>
        共${TablePage.totalPage}页
        <%--共${TablePage.totalRows}行--%>
        第${TablePage.currentPage}页
        每页${TablePage.pageSize}行
        <a href="javascript:afterPage('${TablePage.afterPage}')">下一页</a>
    </div>
<script>
    function beforePage(page) {
        window.location = "${pageContext.request.contextPath}/BookkeepingServlet?currentPage=" + page + "&pageSize=${TablePage.pageSize}";
    }

    function afterPage(page) {
        window.location = "${pageContext.request.contextPath}/BookkeepingServlet?currentPage=" + page + "&pageSize=${TablePage.pageSize}";
    }
</script>
</body>
</html>
